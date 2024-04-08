package by.artem.dao.utils;

import by.artem.dao.classes.*;
import lombok.Cleanup;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.UtilityClass;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
@UtilityClass
public class TestDataImporter {


    public void importData(SessionFactory sessionFactory) {
        @Cleanup Session session = sessionFactory.openSession();

        User edCohen = saveUser(session, "edCohen", "123", RolesEnum.ATHLETE);
        User marlonWilliams = saveUser(session, "marlonWilliams", "123", RolesEnum.ATHLETE);
        User billStarr = saveUser(session, "billStarr", "123", RolesEnum.ATHLETE);
        User leeLabrada = saveUser(session, "leeLabrada", "123", RolesEnum.ATHLETE);
        User donReinhald = saveUser(session, "donReinhald", "123", RolesEnum.ATHLETE);
        User jasonOtto = saveUser(session, "jasonOtto", "123", RolesEnum.ATHLETE);

        saveUserInfo(session, edCohen, "Ed Cohen", 105,
                SportCategoryEnum.MS, LocalDate.of(1987, 02, 21));
        saveUserInfo(session, marlonWilliams, "Marlon Williams", 100,
                SportCategoryEnum.KMS, LocalDate.of(1986, 07, 12));
        saveUserInfo(session, billStarr, "Bill Star", 134,
                SportCategoryEnum.FIRST_CATEGORY, LocalDate.of(1985, 06, 25));
        saveUserInfo(session, leeLabrada, "Lee Labrada", 107,
                SportCategoryEnum.MS, LocalDate.of(1988, 05, 13));
        saveUserInfo(session, jasonOtto, "Jason Otto", 110,
                SportCategoryEnum.FIRST_CATEGORY, LocalDate.of(1989, 03, 04));
        saveUserInfo(session, donReinhald, "Done Reinhald", 123,
                SportCategoryEnum.KMS, LocalDate.of(1991, 05, 24));


        CompetitionCatalog sbdSheffield = saveCompetitionCatalog(session,
                LocalDateTime.of(2024, 05, 23, 9, 00, 00),
                "SBD Sheffield");

        addParticipantsToTheCompetition(session, edCohen, sbdSheffield);
        addParticipantsToTheCompetition(session, marlonWilliams, sbdSheffield);
        addParticipantsToTheCompetition(session, billStarr, sbdSheffield);
        addParticipantsToTheCompetition(session, leeLabrada, sbdSheffield);
        addParticipantsToTheCompetition(session, jasonOtto, sbdSheffield);
        addParticipantsToTheCompetition(session, donReinhald, sbdSheffield);


    }

    private User saveUser(Session session,
                          String login,
                          String password,
                          RolesEnum role
    ) {
        User user = User.builder()
                .login(login)
                .password(password)
                .role(role)
                .build();
        session.save(user);

        return user;
    }

    private void saveUserInfo(Session session,
                              User user,
                              String name,
                              Integer weight,
                              SportCategoryEnum category,
                              LocalDate dateBirth
    ) {
        UserInfo userInfo = UserInfo.builder()
                .name(name)
                .category(category)
                .weight(weight)
                .dateBirth(dateBirth)
                .build();

        userInfo.setUser(user);
        session.save(userInfo);
    }

    private CompetitionCatalog saveCompetitionCatalog(Session session,
                                                      LocalDateTime date,
                                                      String description) {
        session.beginTransaction();
        CompetitionCatalog competitionCatalog = CompetitionCatalog.builder()
                .description(description)
                .dateEvent(date)
                .build();
        session.save(competitionCatalog);
        session.getTransaction().commit();
        return competitionCatalog;
    }

    private void addParticipantsToTheCompetition(Session session,
                                                 User user,
                                                 CompetitionCatalog competitionCatalog) {
        session.beginTransaction();
        user.getCompetitionCatalogs().add(competitionCatalog);
        session.update(user);
        session.getTransaction().commit();
    }

}
