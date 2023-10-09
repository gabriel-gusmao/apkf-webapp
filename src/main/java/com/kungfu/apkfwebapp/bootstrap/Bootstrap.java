package com.kungfu.apkfwebapp.bootstrap;

import com.kungfu.apkfwebapp.domain.*;
import com.kungfu.apkfwebapp.repositories.ExamRepository;
import com.kungfu.apkfwebapp.repositories.MemberRepository;
import com.kungfu.apkfwebapp.repositories.TrainingGroupRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class Bootstrap implements CommandLineRunner {

    private final MemberRepository memberRepository;
    private final TrainingGroupRepository trainingGroupRepository;
    private final ExamRepository examRepository;

    public Bootstrap(MemberRepository memberRepository, TrainingGroupRepository trainingGroupRepository, ExamRepository examRepository) {
        this.memberRepository = memberRepository;
        this.trainingGroupRepository = trainingGroupRepository;
        this.examRepository = examRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadData();

    }

    private void loadData() {
        TrainingGroup trainingGroup1 = new TrainingGroup();
        TrainingGroup trainingGroup2 = new TrainingGroup();

        trainingGroup1.setName("Phoenix Vermelha");
        trainingGroup1.setTrainingLocation("Rua de Baixo, 321");
        trainingGroup1.setCity("Terra dos Piás");

        trainingGroupRepository.save(trainingGroup1);

        trainingGroup2.setName("Macaco Azul");
        trainingGroup2.setTrainingLocation("Rua de Cima, 654");
        trainingGroup2.setCity("Terra dos Piás");

        trainingGroupRepository.save(trainingGroup2);

        Member member1 = new Member();
        Member member2 = new Member();
        Member member3 = new Member();
        Member member4 = new Member();

        member1.setFirstName("Jake");
        member1.setLastName("Finn");
        member1.setBirthDate(LocalDate.of(1999,10,10));
        member1.setStartDate(LocalDate.of(2015,5,5));
        member1.setPhoneNumber("987654321");
        member1.setEmail("jakefinn@email.com");
        member1.setAddress("Rua de Baixo, 123");
        member1.setCity("Terra dos Piás");
        member1.setZipCode("9987-654");
        member1.setPhase(Phase.First);
        member1.setTrainingGroup(trainingGroupRepository.getReferenceById(1));
        member1.setUser(new User());
        member1.getUser().setUserName("jakefinn");
        member1.getUser().setPassword("jake123");
        member1.getUser().setRoles("ROLE_BEGINNER");

        memberRepository.save(member1);

        member2.setFirstName("João");
        member2.setLastName("Pitanga");
        member2.setBirthDate(LocalDate.of(1989,10,10));
        member2.setStartDate(LocalDate.of(2005,5,5));
        member2.setPhoneNumber("123456789");
        member2.setEmail("joaopitanga@email.com");
        member2.setAddress("Rua de Cima, 123");
        member2.setCity("Terra dos Piás");
        member2.setZipCode("9987-321");
        member2.setPhase(Phase.Fifth_level_one);
        member2.setTrainingGroup(trainingGroupRepository.getReferenceById(1));
        member2.setUser(new User());
        member2.getUser().setUserName("joaopitanga");
        member2.getUser().setPassword("joao123");
        member2.getUser().setRoles("ROLE_INSTRUCTOR");

        memberRepository.save(member2);

        member3.setFirstName("Justino");
        member3.setLastName("Dias");
        member3.setBirthDate(LocalDate.of(1995,10,10));
        member3.setStartDate(LocalDate.of(2010,5,5));
        member3.setPhoneNumber("987456321");
        member3.setEmail("justinodias@email.com");
        member3.setAddress("Rua de Baixo, 789");
        member3.setCity("Terra dos Piás");
        member3.setZipCode("9987-654");
        member3.setPhase(Phase.Second);
        member3.setTrainingGroup(trainingGroupRepository.getReferenceById(2));
        member3.setUser(new User());
        member3.getUser().setUserName("justinodias");
        member3.getUser().setPassword("justino123");
        member3.getUser().setRoles("ROLE_BEGINNER");

        memberRepository.save(member3);

        member4.setFirstName("Daniel");
        member4.setLastName("Borges");
        member4.setBirthDate(LocalDate.of(1985,10,10));
        member4.setStartDate(LocalDate.of(2000,5,5));
        member4.setPhoneNumber("123654789");
        member4.setEmail("danielborges@email.com");
        member4.setAddress("Rua de Cima, 159");
        member4.setCity("Terra dos Piás");
        member4.setZipCode("9987-159");
        member4.setPhase(Phase.Eighth_level_two);
        member4.setTrainingGroup(trainingGroupRepository.getReferenceById(2));
        member4.setUser(new User());
        member4.getUser().setUserName("danielborges");
        member4.getUser().setPassword("daniel123");
        member4.getUser().setRoles("ROLE_PROFESSOR");

        memberRepository.save(member4);

//        trainingGroup1.setGroupLeader(member2);
        trainingGroupRepository.save(trainingGroup1);
//        trainingGroup2.setGroupLeader(member4);
        trainingGroupRepository.save(trainingGroup2);

        Exam exam1 = new Exam();

        exam1.setDate(LocalDate.of(2020, 10, 20));
        exam1.setPending(false);
        exam1.setGrade(80);
        exam1.setApproved(true);
        exam1.setMember(memberRepository.getReferenceById(1));

        examRepository.save(exam1);

        Exam exam2 = new Exam();

        exam2.setDate(LocalDate.of(2023, 10, 1));
        exam2.setMember(memberRepository.getReferenceById(3));

        examRepository.save(exam2);
    }
}
