package co.prime.mini.pos.service.impl;

import co.prime.mini.pos.mapper.CompanyMapper;
import co.prime.mini.pos.model.entity.Company;
import co.prime.mini.pos.repository.CompanyRepository;
import co.prime.mini.pos.service.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper itemCompanyMapper;
    @Override
    public Company create(Company company) {
        return companyRepository.save(company);
    }
}
