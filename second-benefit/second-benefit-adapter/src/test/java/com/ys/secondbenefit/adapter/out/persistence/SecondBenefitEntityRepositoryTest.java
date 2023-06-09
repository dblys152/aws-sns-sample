package com.ys.secondbenefit.adapter.out.persistence;

import com.ys.secondbenefit.adapter.config.DataJpaConfig;
import com.ys.secondbenefit.adapter.out.persistence.fixture.SupportSecondBenefitEntityFixture;
import com.ys.secondbenefit.domain.SecondBenefitStatus;
import com.ys.secondbenefit.domain.SecondBenefitType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@Transactional
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = DataJpaConfig.class)
class SecondBenefitEntityRepositoryTest extends SupportSecondBenefitEntityFixture {

    @Autowired
    private SecondBenefitEntityRepository repository;

    private SecondBenefitEntity entity;

    @BeforeEach
    void setUp() {
        entity = new SecondBenefitEntity(
                ANY_SECOND_BENEFIT_ID,
                ANY_USER_ID,
                SecondBenefitType.ORDER,
                SecondBenefitStatus.AVAILABLE,
                ANY_EXPIRED_AT,
                new SecondBenefitTargetMappingEntity(ANY_SECOND_BENEFIT_ID, ANY_ORDER_ID, null),
                null, null, null);
    }

    @Test
    void save() {
        SecondBenefitEntity actual = repository.save(entity);

        assertAll(
                () -> assertThat(actual).isNotNull(),
                () -> assertThat(actual.getId()).isEqualTo(entity.getId()),
                () -> assertThat(actual.getSecondBenefitTargetMappingEntity()).isNotNull()
        );
    }
}