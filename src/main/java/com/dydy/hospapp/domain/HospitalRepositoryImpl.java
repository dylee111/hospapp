package com.dydy.hospapp.domain;

import com.dydy.hospapp.dto.HospitalResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.dydy.hospapp.domain.QHospital.hospital;

public class HospitalRepositoryImpl implements HospitalRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public HospitalRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<HospitalResponseDto> findByHospital(Pageable pageable, String siDo, String sggu) {
        List<HospitalResponseDto> hospitalList = queryFactory
                .select(Projections.constructor(HospitalResponseDto.class,
                        hospital.yadmNm,
                        hospital.addr,
                        hospital.telno,
                        hospital.pcrPsblYn,
                        hospital.sidoCdNm
                ))
                .from(hospital, hospital)
                .where(siDoEq(siDo), sgguEq(sggu), hospital.pcrPsblYn.eq("Y"))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long countQuery = queryFactory
                .select(hospital.id.count())
                .from(hospital, hospital)
                .where(hospital.pcrPsblYn.eq("Y"))
                .fetchFirst();

        return new PageImpl<>(hospitalList, pageable, countQuery);
    }

    @Override
    public List<HospitalResponseDto> ListHospital() {
        return queryFactory
                .select(Projections.constructor(HospitalResponseDto.class,
                        hospital.yadmNm,
                        hospital.addr,
                        hospital.telno,
                        hospital.pcrPsblYn,
                        hospital.sidoCdNm
                ))
                .from(hospital, hospital)
                .where(hospital.pcrPsblYn.eq("Y"))
                .fetch()
                ;
    }

    private BooleanExpression siDoEq(String siDo) {
        return StringUtils.hasText(siDo) ? hospital.sidoCdNm.eq(siDo) : null;
    }

    private BooleanExpression sgguEq(String sggu) {
        return StringUtils.hasText(sggu) ? hospital.sgguCdNm.eq(sggu) : null;
    }

}
