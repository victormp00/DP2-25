/*
 * AuthenticatedProviderCreateService.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.authenticated.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.roles.Provider;
import acme.entities.spam.Spam;
import acme.entities.spam.Threshold;
import acme.features.administrator.spam.AdminSpamCreateService;
import acme.features.administrator.spam.AdminSpamRepository;
import acme.features.administrator.threshold.ThresholdRepository;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedProviderCreateService implements AbstractCreateService<Authenticated, Provider> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedProviderRepository repository;
	
	@Autowired
	private AdminSpamRepository spamRepository;
	
	@Autowired
	protected AdminSpamCreateService spamService;
	
	@Autowired
	protected ThresholdRepository	thresholdRepository;

	// AbstractCreateService<Authenticated, Provider> interface ---------------


	@Override
	public boolean authorise(final Request<Provider> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Provider> request, final Provider entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Provider> request, final Provider entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "company", "sector");
	}

	@Override
	public Provider instantiate(final Request<Provider> request) {
		assert request != null;

		Provider result;
		Principal principal;
		int userAccountId;
		UserAccount userAccount;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		userAccount = this.repository.findOneUserAccountById(userAccountId);

		result = new Provider();
		result.setUserAccount(userAccount);

		return result;
	}

	@Override
	public void validate(final Request<Provider> request, final Provider entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final List<Spam> spam= (List<Spam>) this.spamRepository.findSpam();
		final Threshold threshold=this.thresholdRepository.findSpamEntity(35);
		final boolean censuraCompany = Threshold.censura(entity.getCompany(), spam, threshold.getThreshold());
		final boolean censuraSector = Threshold.censura(entity.getSector(), spam, threshold.getThreshold());
		
		if(censuraCompany) {
			errors.add("company", "this company is spam ");
		}

		if(censuraSector) {
			errors.add("sector", "this sector is spam ");
		}
	}

	@Override
	public void create(final Request<Provider> request, final Provider entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Provider> request, final Response<Provider> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
