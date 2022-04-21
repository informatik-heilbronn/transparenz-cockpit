package de.hhn.seb.labsw.transparentcockpit.backend.mongo.repositorys.project.report;

import de.hhn.seb.labsw.transparentcockpit.backend.exceptions.reporitory.NotFoundException;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.config.MongoConfig;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.encoder.Base64Encoder;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.encoder.project.report.ProjectReportMdbEncoder;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.report.ProjectReportMdb;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.repositorys.RepositoryMdb;
import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.experimental.filters.Filters;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mongo Implementation ProjectReport Repository.
 */
public class ProjectReportRepositoryMdb extends RepositoryMdb {

    private final Datastore datastore;


    public ProjectReportRepositoryMdb(MongoConfig mongoConfig) {
        this.datastore = init(mongoConfig);
    }


    public void saveProjectReportMdb(ProjectReportMdb projectReportMdb) {
        datastore.save(ProjectReportMdbEncoder.encode(projectReportMdb));
    }

    public void updateProjectReportMdb(String projectReportId, ProjectReportMdb projectReportMdb) {
        datastore.find(ProjectReportMdb.class)
                .filter(Filters.eq("projectNumber", Base64Encoder.encode(projectReportId)))
                .delete();

        datastore.save(ProjectReportMdbEncoder.encode(projectReportMdb));
    }

    public ProjectReportMdb getProjectReportMbdById(String projectReportId)
            throws NotFoundException {
        Query<ProjectReportMdb> morphiaQuery = datastore.find(ProjectReportMdb.class)
                .filter(Filters.eq("projectNumber", Base64Encoder.encode(projectReportId)));

        if (morphiaQuery.count() == 0) {
            throw new NotFoundException("There is no ProjectReport with the ProjectNumber: " + projectReportId);
        }

        return ProjectReportMdbEncoder.decode(morphiaQuery.first());
    }

    public List<ProjectReportMdb> getAllProjectReportMdb() {
        return datastore.find(ProjectReportMdb.class)
                .stream()
                .map(ProjectReportMdbEncoder::decode)
                .collect(Collectors.toList());
    }
}
