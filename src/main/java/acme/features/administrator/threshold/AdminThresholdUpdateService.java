
package acme.features.administrator.threshold;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.Spam;
import acme.entities.spam.Threshold;
import acme.features.administrator.spam.AdminSpamRepository;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Administrator;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdminThresholdUpdateService implements AbstractUpdateService<Administrator, Threshold> {

	@Autowired
	protected AdminSpamRepository	spamWordRepository;

	@Autowired
	protected ThresholdRepository	repository;


	@Override
	public boolean authorise(final Request<Threshold> request) {
		assert request != null;
		;
		return true;

	}

	@Override
	public void bind(final Request<Threshold> request, final Threshold entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Threshold> request, final Threshold entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "threshold");

	}

	@Override
	public Threshold findOne(final Request<Threshold> request) {
		assert request != null;
		Threshold result;
		result = this.repository.findSpamEntity();

		return result;
	}

	@Override
	public void validate(final Request<Threshold> request, final Threshold entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<Threshold> request, final Threshold entity) {
		assert request != null;
		assert entity != null;
		final List<Spam> spamWords = (List<Spam>) this.spamWordRepository.findSpam();
		entity.setSpamWords(spamWords);
		this.repository.save(entity);
	}
	@Override
	public void onSuccess(final Request<Threshold> request, final Response<Threshold> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
