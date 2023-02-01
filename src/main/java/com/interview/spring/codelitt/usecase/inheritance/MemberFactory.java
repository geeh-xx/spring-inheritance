package com.interview.spring.codelitt.usecase.inheritance;

import com.interview.spring.codelitt.enums.MemberTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class MemberFactory {

    private Map<MemberTypeEnum, MemberActions> members;

    @Autowired
    public MemberFactory(Set<MemberActions> memberSet) {
        createStrategy(memberSet);
    }

    public MemberActions getMember(MemberTypeEnum memberType) {
        return members.get(memberType);
    }
    private void createStrategy(Set<MemberActions> memberSet) {
        members = new HashMap<MemberTypeEnum, MemberActions>();
        memberSet.forEach(
                strategy -> members.put(strategy.getMemberType(), strategy));
    }

}
