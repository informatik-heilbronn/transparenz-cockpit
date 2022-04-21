package de.hhn.seb.labsw.transparentcockpit.backend.mongo.repositorys.project.template;

import de.hhn.seb.labsw.transparentcockpit.backend.exceptions.reporitory.NotFoundException;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.config.MongoConfig;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.encoder.Base64Encoder;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.encoder.project.template.ProjectReportTemplateMdbEncoder;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.internal.entity.project.template.ProjectReportTemplateMdb;
import de.hhn.seb.labsw.transparentcockpit.backend.mongo.repositorys.RepositoryMdb;
import dev.morphia.Datastore;
import dev.morphia.query.Query;
import dev.morphia.query.experimental.filters.Filters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Mongo Implementation ProjectReportTemplate Repository.
 */
public class ProjectReportTemplateRepositoryMdb extends RepositoryMdb {

  private final Datastore datastore;


  public ProjectReportTemplateRepositoryMdb(MongoConfig mongoConfig) {
    this.datastore = init(mongoConfig);
  }


  public void saveProjectReportTemplateMdb(ProjectReportTemplateMdb projectReportTemplateMdb) {
    datastore.save(ProjectReportTemplateMdbEncoder.encode(projectReportTemplateMdb));
  }

  public ProjectReportTemplateMdb getProjectReportTemplateMdbById(String projectReportTemplateId) {
    Query<ProjectReportTemplateMdb> morphiaQuery = datastore.find(ProjectReportTemplateMdb.class)
        .filter(Filters.eq("templateId", Base64Encoder.encode(projectReportTemplateId)));

    if (morphiaQuery.count() == 0) {
      throw new NotFoundException("There is no ProjectReportTemplate with the TemplateId: " + projectReportTemplateId);
    }

    return ProjectReportTemplateMdbEncoder.decode(morphiaQuery.first());
  }
}
