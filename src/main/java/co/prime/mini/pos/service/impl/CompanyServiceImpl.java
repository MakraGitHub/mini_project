package co.prime.mini.pos.service.impl;

import co.prime.mini.pos.exception.ResourceNotFoundException;
import co.prime.mini.pos.mapper.CompanyMapper;
import co.prime.mini.pos.models.entity.Company;
import co.prime.mini.pos.models.respone.CompanyResponse;
import co.prime.mini.pos.repository.CompanyRepository;
import co.prime.mini.pos.service.CompanyService;
import co.prime.mini.pos.service.GeneralFileService;
import co.prime.mini.pos.service.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper itemCompanyMapper;
    private final GeneralFileService generalFileService;

    @Value("${file.server-path}")
    private String fileServerPath;
    @Override
    public Company create(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company getById(Long id) {
        return companyRepository.findByIdAndIsDeletedFalse(id).orElseThrow(()->
                new ResourceNotFoundException("Company",id));
    }

    @Override
    public Company update(Long id, Company newCompany) {
        Company upCompany = getById(id);
        upCompany.setCompanyLocalName(newCompany.getCompanyLocalName());
        upCompany.setCompanyEngName(newCompany.getCompanyEngName());
        upCompany.setCompanyEmail(newCompany.getCompanyEmail());
        upCompany.setCompanyPhone(newCompany.getCompanyPhone());
        upCompany.setVatNumber(newCompany.getVatNumber());
        upCompany.setImagePath(newCompany.getImagePath());
        upCompany.setImage(newCompany.getImage());
        return companyRepository.save(upCompany);
    }

    @Override
    public List<CompanyResponse> getCustomers() {
        return companyRepository.findByIsDeletedIsFalseOrderByIdDesc()
                .stream()
                .map(itemCompanyMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Company delete(Long id) {
        Company byId = getById(id);
        byId.setIsDeleted(true);
        Company save = companyRepository.save(byId);
        return save;
    }

    @Override
    public Company saveImage(Long id, MultipartFile file) throws IOException {
        //Upload image to change name image
        String newFileName = generalFileService.generalFile(file.getOriginalFilename());
        //After change file it is make transfer image to file path.
        String destinationPath = fileServerPath + newFileName;
        file.transferTo(new File(destinationPath));

        Company saveImageCompany = getById(id);
        //Save name path into db.
        saveImageCompany.setImagePath(newFileName);
        return companyRepository.save(saveImageCompany);
    }

    @Override
    public Page<CompanyResponse> findWithPagination(Map<String, String> params) {
        int pageLimit = PageUtil.DEFAULT_PAGE_LIMIT;
        if(params.containsKey(PageUtil.PAGE_LIMIT)){
            pageLimit = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
        }
        int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
        if(params.containsKey(PageUtil.PAGE_NUMBER)){
            pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
        }
        Pageable pageable = PageUtil.getPageable(pageNumber, pageLimit);
        Page<CompanyResponse> page =
                companyRepository.findByIsDeletedIsFalseOrderByIdDesc(pageable)
                        .map(itemCompanyMapper::toDTO);
        return page;
    }

}
