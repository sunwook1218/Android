package com.example.login;

public class Member {

    public static String memberId;
    public static String memberPw;
    public static String memberName;
    public static Integer memberAge;

    public Member() {}

    public Member(String memberId, String memberPw, String memberName, Integer memberAge) {
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberName = memberName;
        this.memberAge = memberAge;
    }

}
