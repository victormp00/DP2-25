
package acme.features.administrator.spam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spam.Spam;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Administrator;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class AdminSpamCreateService implements AbstractCreateService<Administrator, Spam> {

	@Autowired
	protected AdminSpamRepository repository;


	@Override
	public boolean authorise(final Request<Spam> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Spam> request, final Spam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Spam> request, final Spam entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "spamEs", "spamEn");
		model.setAttribute("spamId", entity.getId());
	}

	@Override
	public Spam instantiate(final Request<Spam> request) {
		assert request != null;

		Spam result;

		result = new Spam();
		result.setSpamEn("Spam en ");
		result.setSpamEs("Spam es ");
		return result;
	}

	@Override
	public void validate(final Request<Spam> request, final Spam entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<Spam> request, final Spam entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Spam> request, final Response<Spam> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

//	public Boolean censura(final String campo, final List<Spam> spam) {
//		Boolean res = false;
//		final String[] palabras = campo.split(" ");
//		final List<String> palabrasSep = Arrays.asList(palabras);
//		int i = 0;
//		for (final String p : palabrasSep) {
//			for (final Spam s : spam) {
//				if (s.getSpamEn().equals(s.getSpamEs())) {
//					if (p.equals(s.getSpamEs())) {
//						i++;
//					}
//				} else {
//					if (p.equals(s.getSpamEn())) {
//						i++;
//					}
//					if (p.equals(s.getSpamEs())) {
//						i++;
//					}
//				}
//				if (s.getThreshold() < ((double) i / palabras.length) * 100) {
//					res = true;
//				}
//
//			}
//		}
//
//		return res;
//	}

}
