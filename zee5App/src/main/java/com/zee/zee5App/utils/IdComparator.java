package com.zee.zee5App.utils;

import com.zee.zee5App.dto.User;
import java.util.Comparator;

public class IdComparator implements Comparator<User> {

	@Override
	public int compare(User o1, User o2) {
		// TODO Auto-generated method stub
		return o1.getUserId().compareTo(o2.getUserId());		
	}

}
