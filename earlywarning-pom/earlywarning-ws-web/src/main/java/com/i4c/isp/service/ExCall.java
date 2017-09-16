package com.i4c.isp.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;

import com.i4c.antares.taskmanager.workflow.WorkflowException;
import com.i4c.antares.taskmanager.workflow.WorkflowFacadeLocal;
import com.i4c.antares.taskmanager.workflow.WorkflowParameters;
import com.i4c.antares.taskmanager.workflow.WorkflowRepositoryLocal;
import com.i4c.antares.taskmanager.workflow.WorkflowRun;
import com.i4c.antares.taskmanager.workflow.WorkflowRunResult;
import com.i4c.antares.usersactions.business.UserManager;

@WebService(name = "executeDailyBatch", serviceName = "executeDailyBatchWS")
@Stateless
public class ExCall {

	private static final String USER_ID = "admin";

	@EJB
	WorkflowRepositoryLocal workflowRepository;
	@EJB
	UserManager userManager;
	@EJB
	WorkflowFacadeLocal executor;

	@WebMethod
	@WebResult(name = "Result")
	public String executeWorkflow(String workflowName) {
		StringBuffer message = new StringBuffer();
		WorkflowRunResult runResult;
		try {

			if (!executor.existsWorkflow(workflowName)) {
				message.append("Error - workflow not exist " + workflowName);
			} else {

				if (!isRunning(workflowName)) {
					runResult = executor.executeWorkflowByName(workflowName,
							new WorkflowParameters(), USER_ID);

					if (runResult.allStepRunsSuccessful()) {
						message.append("WorkFlow executed");
					} else {
						message.append("Error - execute workflow");
					}
				}
				else
					message.append("Error - workflow running");
			}
		} catch (WorkflowException e) {
			message.append("Error " + e.getMessage());
		}

		return message.toString();
	}

	private boolean isRunning(String workflowName) {
		List<WorkflowRun> workflowRuns = executor.findAllRunningWorkflowRuns();

		for (WorkflowRun run : workflowRuns) {
			if (run.getWorkflow().getName().equalsIgnoreCase(workflowName)) {
				return true;
			}

		}

		return false;
	}

}
