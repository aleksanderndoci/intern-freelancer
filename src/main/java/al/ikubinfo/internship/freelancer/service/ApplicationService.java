package al.ikubinfo.internship.freelancer.service;

import al.ikubinfo.internship.freelancer.entity.ApplicationKey;
import al.ikubinfo.internship.freelancer.model.ApplicationModel;

public interface ApplicationService {

	ApplicationModel apply(ApplicationKey primaryKey);
	ApplicationModel getApplicationByPK(ApplicationKey key);
}
