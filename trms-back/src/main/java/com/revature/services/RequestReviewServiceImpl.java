package com.revature.services;

import java.time.LocalDateTime;
import java.util.Set;

import com.revature.beans.Comment;
import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.beans.Status;
import com.revature.data.CommentDAO;
import com.revature.data.ReimbursementDAO;
import com.revature.utils.DAOFactory;

public class RequestReviewServiceImpl implements RequestReviewService {
	
	private CommentDAO commd = DAOFactory.getCommentDAO();
	private ReimbursementDAO reqd = DAOFactory.getReimbursementDAO();
	
	

	

	
	@Override
	public Set<Reimbursement> getPendingReimbursements(Employee approver, int i) {
		return reqd.getPendByApprover(approver, i);
	}

	@Override
	public void approveRequest(Reimbursement request) {
		Status s = new Status();
		request.setStatus(s);
		reqd.update(request);
	}

	@Override
	public void rejectRequest(Reimbursement request) {
		Status s = new Status();
		request.setStatus(s);
		reqd.update(request);
	}

	@Override
	public void rejectRequest(Reimbursement request, Comment comment) {
		Status stat = new Status();
		stat.setStatusId(4);
			if(reqd.getById(request.getReqId()) != null) {
				request.setStatus(stat);
				reqd.update(request);
				request = reqd.getById(request.getReqId());
				comment.setSentAt(LocalDateTime.now());
				commd.create(comment);
			}

	}

	

}
