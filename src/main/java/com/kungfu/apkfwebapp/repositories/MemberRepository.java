package com.kungfu.apkfwebapp.repositories;

import com.kungfu.apkfwebapp.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {
}
