package co.prime.mini.pos.service;

import co.prime.mini.pos.models.entity.Company;
import co.prime.mini.pos.models.respone.CompanyResponse;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface CompanyService {
    Company create(Company company);
    Company getById(Long id);
    Company update(Long id, Company newCompany);
    List<CompanyResponse> getCustomers();
    Company delete(Long id);
    Company saveImage(Long id, MultipartFile file) throws Exception;
    Page<CompanyResponse> findWithPagination(Map<String, String> params);

}
