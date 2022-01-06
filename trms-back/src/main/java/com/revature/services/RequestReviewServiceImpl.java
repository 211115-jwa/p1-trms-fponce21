package com.revature.services;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.revature.beans.Comment;
import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.beans.Status;
import com.revature.data.CommentDAO;
import com.revature.data.ReimbursementDAO;
import com.revature.utils.DAOFactory;

public class RequestReviewServiceImpl implements RequestReviewService {
	
	private CommentDAO commentDao = DAOFactory.getCommentDAO();
	private ReimbursementDAO reqDao = DAOFactory.getReimbursementDAO();
	
	

	

	
	@Override
	public Set<Reimbursement> getPendingReimbursements(Employee approver) {
		Set<Reimbursement> requests = reqDao.getAll();
		
		Set<Reimbursement> req = new HashSet<>();
		for (Reimbursement request : requests) {
			if (( request.getStatus().getName().contains("pending"))) {
				req.add(request);
			}
				
		}
		requests = req;
		
		return requests;
	}

	@Override
	public void approveRequest(Reimbursement request) {
		Status stat = new Status();
		stat.setStatusId(3);
		if (reqDao.getById(request.getReqId()) != null) {
			request.setStatus(stat);
			reqDao.update(request);
		}

	}

	@Override
	public void rejectRequest(Reimbursement request) {
		Status stat = new Status();
		stat.setStatusId(4);
			if (reqDao.getById(request.getReqId()) != null) {
				request.setStatus(stat);
				reqDao.update(request);
			}

	}

	@Override
	public void rejectRequest(Reimbursement request, Comment comment) {
		Status stat = new Status();
		stat.setStatusId(4);
			if(reqDao.getById(request.getReqId()) != null) {
				request.setStatus(stat);
				reqDao.update(request);
				request = reqDao.getById(request.getReqId());
				comment.setSentAt(LocalDateTime.now());
				commentDao.create(comment);
			}

	}

}
