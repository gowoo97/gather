package com.elice.gather.service.interfaces;

import com.elice.gather.entity.Member;

public interface MemberService {

	Member enrollMember(String userId, String password);

	
}
