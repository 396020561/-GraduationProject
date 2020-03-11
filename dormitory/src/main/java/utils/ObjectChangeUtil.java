package utils;

import java.util.ArrayList;

import com.nchu.dto.AccAttendResult;
import com.nchu.entity.AccAttend;
import com.nchu.entity.Access;
import com.nchu.entity.User;
import com.nchu.entity.VisitHistory;

public class ObjectChangeUtil {
	
	
	public static ArrayList<AccAttendResult> accExce(ArrayList<AccAttend> list,User user){
		ArrayList<AccAttendResult> result = new ArrayList<>();
		for(AccAttend accAttend : list){
			AccAttendResult accAttendResult = new AccAttendResult();
			accAttendResult.setUid(user.getUid());
			accAttendResult.setName(user.getName());
			accAttendResult.setClassid(user.getClassid());
			accAttendResult.setCollageid(user.getCollageid());
			accAttendResult.setCategory(accAttend.getCategory());
			accAttendResult.setDevid(accAttend.getDevid());
			accAttendResult.setOccurTime(accAttend.getOccurTime());
			result.add(accAttendResult);
		}
		return result;
	}
	
	public static ArrayList<AccAttendResult> accVisit(ArrayList<VisitHistory> list,User user){
		ArrayList<AccAttendResult> result = new ArrayList<>();
		for(VisitHistory accAttend : list){
			AccAttendResult accAttendResult = new AccAttendResult();
			accAttendResult.setUid(user.getUid());
			accAttendResult.setName(accAttend.getVname());
			accAttendResult.setOccurTime(accAttend.getCreateTime());
			accAttendResult.setPlace(accAttend.getPlace());
			result.add(accAttendResult);
		}
		return result;
	}
	
	public static ArrayList<AccAttendResult> accUser(ArrayList<Access> list,User user){
		ArrayList<AccAttendResult> result = new ArrayList<>();
		for(Access accAttend : list){
			AccAttendResult accAttendResult = new AccAttendResult();
			accAttendResult.setUid(user.getUid());
			accAttendResult.setName(user.getName());
			accAttendResult.setClassid(user.getClassid());
			accAttendResult.setCollageid(user.getCollageid());
			accAttendResult.setDirection(accAttend.getDirection());
			accAttendResult.setDevid(accAttend.getDevid());
			accAttendResult.setOccurTime(accAttend.getCreateTime());
			accAttendResult.setPlace(accAttend.getPlace());
			result.add(accAttendResult);
		}
		return result;
	}
}	
