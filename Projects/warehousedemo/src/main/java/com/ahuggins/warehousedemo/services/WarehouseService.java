// package com.ahuggins.warehousedemo.services;

// import java.util.List;
// import java.util.Optional;
// import java.util.stream.Collectors;

// import org.springframework.stereotype.Service;

// import com.ahuggins.warehousedemo.dtos.AdministratorDto;
// import com.ahuggins.warehousedemo.models.Administrator;
// import com.ahuggins.warehousedemo.models.Warehouse;
// import com.ahuggins.warehousedemo.repositories.WarehouseRepository;

// @Service
// public class WarehouseService {
//     private WarehouseRepository repo;

//     public WarehouseService(WarehouseRepository repo){
//         this.repo = repo;
//     }

//     public List<Warehouse> getAllAdministrators(){
//         return repo.findAll();
//     }

//     public Optional<Warehouse> getAdministratorById(int id){
//         return repo.findById(id);
//     }

//     public Optional<AdministratorDto> createAdministrator(String authorization) {
//         if(repo.findByCompanyName(admin.getCompanyName()).isEmpty()){
//             try {
//                 String companyName=admin.getCompanyName(), password=SecurityService.hashString(admin.getPassword());
//                 admin.setPassword(password);
//                 return Optional.of(mapper.toDto(repo.save(admin)));
//             } catch (Exception e) {
//                 e.printStackTrace();
//             }
//         }
        
//         return Optional.empty();
//     }

//     public Optional<AdministratorDto> updateAdministrator(String authorization, Administrator admin) throws IllegalAccessException{
//         if(!SecurityService.validateAdmin(authorization, admin)) throw new IllegalAccessException("JWT not authorized for this administrator.");

//         if(repo.findById(admin.getId()).isPresent()){
//             return Optional.of(mapper.toDto(repo.save(admin)));
//         }

//         return Optional.empty();
//     }

//     public void deleteAdministrator(String authorization, int id, String companyName) throws IllegalAccessException{
//         Administrator admin = new Administrator(id, companyName);
//         if(!SecurityService.validateAdmin(authorization, admin)) throw new IllegalAccessException("JWT not authorized for this administrator.");

//         repo.deleteById(id);
//     }
// }
