package de.hhn.seb.labsw.transparentcockpit.backend.mongo.repositorys.project.report;

import de.hhn.seb.labsw.transparentcockpit.backend.exceptions.reporitory.NotFoundException;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.config.MongoConfig;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.encoder.Base64Encoder;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.encoder.project.report.ProjectReportHistoryMdbEncoder;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.report.ProjectReportHistoryMdb;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.report.ProjectReportMdb;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.repositorys.RepositoryMdb;
import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.experimental.filters.Filters;

import java.time.OffsetDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Mongo Implementation ProjectReportHistory Repository.
 */
public class ProjectReportHistoryRepositoryMdb extends RepositoryMdb {

    private final Datastore datastore;


    public ProjectReportHistoryRepositoryMdb(MongoConfig mongoConfig) {
        this.datastore = init(mongoConfig);
    }


    public void saveProjectReportHistoryMdb(ProjectReportHistoryMdb projectReportHistoryMdb) {
        datastore.save(ProjectReportHistoryMdbEncoder.encode(projectReportHistoryMdb));
    }

    public void updateProjectReportHistoryMdb(String projectReportId,
                                              ProjectReportMdb projectReportMdb)
            throws NotFoundException {
        Query<ProjectReportHistoryMdb> morphiaQuery = datastore.find(ProjectReportHistoryMdb.class)
                .filter(Filters.eq("projectNumber", Base64Encoder.encode(projectReportId)));

        if (morphiaQuery.count() == 0) {
            throw new NotFoundException("There is no ProjectReportHistory with the ProjectNumber: " + projectReportId);
        }

        ProjectReportHistoryMdb projectReportHistoryMdb = ProjectReportHistoryMdbEncoder.decode(morphiaQuery.first());
        String lastEditedOffsetDateTime = OffsetDateTime.now().toString();
        projectReportHistoryMdb.setLastEdit(lastEditedOffsetDateTime);
        projectReportHistoryMdb.addHistory(lastEditedOffsetDateTime, projectReportMdb);

        datastore.find(ProjectReportHistoryMdb.class)
                .filter(Filters.eq("projectNumber", Base64Encoder.encode(projectReportId)))
                .delete();

        datastore.save(ProjectReportHistoryMdbEncoder.encode(projectReportHistoryMdb));
    }

    public ProjectReportHistoryMdb getProjectReportHistoryMdbById(String projectReportId) {
        Query<ProjectReportHistoryMdb> morphiaQuery = datastore.find(ProjectReportHistoryMdb.class)
                .filter(Filters.eq("projectNumber", Base64Encoder.encode(projectReportId)));

        if (morphiaQuery.count() == 0) {
            throw new NotFoundException("There is no ProjectReportHistory with the ProjectNumber: " + projectReportId);
        }

        return ProjectReportHistoryMdbEncoder.decode(morphiaQuery.first());
    }
}
