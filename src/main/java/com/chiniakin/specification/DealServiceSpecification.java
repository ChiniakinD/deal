package com.chiniakin.specification;

import com.chiniakin.entity.Deal;
import com.chiniakin.model.deal.DealFilter;
import com.chiniakin.model.deal.DealStatusResponse;
import com.chiniakin.model.deal.DealTypeResponse;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public final class DealServiceSpecification {

    private DealServiceSpecification() {
    }

    public static Specification<Deal> buildSpecification(DealFilter filter) {
        return Specification.where(byId(filter.getId()))
                .and(byDescription(filter.getDescription()))
                .and(byActive())
                .and(byAgreementNumber(filter.getAgreementNumber()))
                .and(byAgreementDate(filter.getAgreementDateFrom(), filter.getAgreementDateTo()))
                .and(byAvailabilityDate(filter.getAvailabilityDateFrom(), filter.getAvailabilityDateTo()))
                .and(byType(filter.getDealTypeResponses()))
                .and(byStatus(filter.getDealStatusResponses()))
                .and(byCloseDate(filter.getCloseDtFrom(), filter.getCloseDtTo()))
                .and(byContractorId(filter.getContractorId()))
                .and(byContractorName(filter.getName()))
                .and(byContractorInn(filter.getInn()));
    }

    private static Specification<Deal> byContractorInn(String inn) {
        return (root, query, criteriaBuilder) -> {
            if (inn == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("dealContractor").get("inn"), "%" + inn + "%");
        };
    }

    private static Specification<Deal> byContractorName(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("dealContractor").get("name"), "%" + name + "%");
        };
    }

    private static Specification<Deal> byContractorId(String contractorId) {
        return (root, query, criteriaBuilder) -> {
            if (contractorId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("dealContractor").get("id"), "%" + contractorId + "%");
        };
    }

    private static Specification<Deal> byActive() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isActive"), true);
    }

    private static Specification<Deal> byId(UUID id) {
        return (root, query, criteriaBuilder) -> {
            if (id == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("id"), id);
        };
    }

    private static Specification<Deal> byDescription(String description) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(description)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("description"), description);
        };
    }

    private static Specification<Deal> byAgreementNumber(String agreementNumber) {
        return (root, query, criteriaBuilder) -> {
            if (!StringUtils.hasText(agreementNumber)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("agreementNumber"), "%" + agreementNumber + "%");
        };
    }

    private static Specification<Deal> byAgreementDate(LocalDate from, LocalDate to) {
        return (root, query, criteriaBuilder) -> {
            if (from == null || to == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.between(root.get("agreementDate"), from, to);
        };
    }

    private static Specification<Deal> byAvailabilityDate(LocalDate from, LocalDate to) {
        return (root, query, criteriaBuilder) -> {
            if (from == null || to == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.between(root.get("availabilityDate"), from, to);
        };
    }

    private static Specification<Deal> byType(List<DealTypeResponse> types) {
        return (root, query, criteriaBuilder) -> {
            if (types == null || types.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return root.get("type").in(types.stream().map(DealTypeResponse::getId).toList());
        };
    }

    private static Specification<Deal> byStatus(List<DealStatusResponse> statuses) {
        return (root, query, criteriaBuilder) -> {
            if (statuses == null || statuses.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return root.get("status").in(statuses.stream().map(DealStatusResponse::getId).toList());
        };
    }

    private static Specification<Deal> byCloseDate(LocalDateTime from, LocalDateTime to) {
        return (root, query, criteriaBuilder) -> {
            if (from == null || to == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.between(root.get("closeDt"), from, to);
        };
    }

}
