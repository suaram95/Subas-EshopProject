package com.example.subaseshopproject.controller;

import com.example.subaseshopproject.dto.BlogRequestDto;
import com.example.subaseshopproject.dto.ProductRequestDto;
import com.example.subaseshopproject.model.*;
import com.example.subaseshopproject.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.TableGenerator;
import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final BlogCategoryService blogCategoryService;
    private final CategoryService categoryService;
    private final ProductService productService;
    private final BrandService brandService;
    private final BlogService blogService;
    private final TeamMemberService teamMemberService;

    @Value("${file.upload.dir}")
    private String uploadDir;

    @GetMapping("/admin")
    public String adminPage(ModelMap map, @RequestParam(value = "msg", required = false) String msg) {
        map.addAttribute("blogCategories", blogCategoryService.findAll());
        map.addAttribute("productCategories", categoryService.findAll());
        map.addAttribute("brands", brandService.findAll());
        map.addAttribute("teamMembers", teamMemberService.findAll());
        map.addAttribute("msg", msg);
        return "/adminPanel/admin";
    }

    @PostMapping("/admin/addBrand")
    public String addBrand(@ModelAttribute("brand") Brand brand,
                           @RequestParam("brandImage") MultipartFile multipartFile) throws IOException {
        String brandPic = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
        File image = new File(uploadDir, brandPic);
        multipartFile.transferTo(image);
        brand.setPicUrl(brandPic);
        brandService.save(brand);
        return "redirect:/admin?msg=Brand was added!";
    }

    @PostMapping("/admin/addProduct")
    public String addProduct(@ModelAttribute("product") ProductRequestDto productRequestDto,
                             @RequestParam("image") MultipartFile multipartFile) throws IOException {

        String productPic = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
        File image = new File(uploadDir, productPic);
        multipartFile.transferTo(image);

        Product product = Product.builder()
                .name(productRequestDto.getName())
                .brand(productRequestDto.getBrand())
                .operatingSystem(productRequestDto.getOperatingSystem())
                .price(productRequestDto.getPrice())
                .color(productRequestDto.getColor())
                .productType(productRequestDto.getProductType())
                .productListType(productRequestDto.getProductListType())
                .description(productRequestDto.getDescription())
                .picUrl(productPic)
                .category(productRequestDto.getCategory())
                .build();
        productService.save(product);
        String msg = "Product was successfully added!";
        return "redirect:/admin?msg=" + msg;
    }

    @PostMapping("/admin/addBlog")
    public String addBlog(@ModelAttribute("blog") BlogRequestDto blogRequestDto,
                          @RequestParam("blogImage") MultipartFile multipartFile) throws IOException {
        Optional<Blog> blogByName = blogService.findBlogByName(blogRequestDto.getName());
        if (blogByName.isPresent()) {
            return "redirect:/admin?msg=Blog with name " + blogRequestDto.getName() + " already exists!";
        }

        String blogPic = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
        File image = new File(uploadDir, blogPic);
        multipartFile.transferTo(image);

        Blog blog = Blog.builder()
                .name(blogRequestDto.getName())
                .text(blogRequestDto.getText())
                .createdDate(blogRequestDto.getCreatedDate())
                .picUrl(blogPic)
                .category(blogRequestDto.getCategory())
                .build();
        blogService.save(blog);
        return "redirect:/admin?msg=Blog was added!";
    }

    @PostMapping("/admin/addTeamMember")
    public String addTeamMember(@ModelAttribute("teamMember")TeamMember teamMember,
                                @RequestParam("image") MultipartFile multipartFile) throws IOException {

        String teamMemberPic = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
        File image = new File(uploadDir, teamMemberPic);
        multipartFile.transferTo(image);

        teamMember.setPicUrl(teamMemberPic);
        teamMemberService.save(teamMember);
        return "redirect:/admin?msg=Member was successfully added";
    }

    @GetMapping("/admin/editMember")
    public String editMember(@RequestParam("id") long id, ModelMap map){
        Optional<TeamMember> memberById = teamMemberService.findById(id);
        if (!memberById.isPresent()){
            return "redirect:/admin";
        }
        TeamMember teamMember = memberById.get();
        map.addAttribute("teamMember", teamMember);
        return "/adminPanel/editMember";

    }

    @PostMapping("/admin/modifyMember")
    public String modifyMember(@RequestParam("memberId") long id,
                               @RequestParam("name") String name,
                               @RequestParam("surname") String surname,
                               @RequestParam("bio") String bio,
                               @RequestParam("memberType") String memberType){
        Optional<TeamMember> memberById = teamMemberService.findById(id);
        if (!memberById.isPresent()){
            return "redirect:/admin";
        }
        TeamMember teamMember = memberById.get();
        teamMember.setName(name);
        teamMember.setSurname(surname);
        teamMember.setBio(bio);
        teamMember.setMemberType(MemberType.valueOf(memberType));
        teamMemberService.save(teamMember);
        return "redirect:/admin?msg=Member data was successfully edited";
    }

    @PostMapping("/admin/changePicture")
    public String changeMemberPicture(@RequestParam("memberId") long id,
                                      @RequestParam("image") MultipartFile multipartFile) throws IOException {
        Optional<TeamMember> memberById = teamMemberService.findById(id);
        if (memberById.isPresent()){
            TeamMember teamMember = memberById.get();

            String teamMemberPic = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File image = new File(uploadDir, teamMemberPic);
            multipartFile.transferTo(image);

            teamMember.setPicUrl(teamMemberPic);
            teamMemberService.save(teamMember);
            return "redirect:/admin?msg=Picture was successfully changed";
        }
        return "redirect:/admin?msg=Something went wrong";
    }

    @GetMapping("/admin/removeMember")
    public String removeMember(@RequestParam("id") long id){
        Optional<TeamMember> memberById = teamMemberService.findById(id);
        if (memberById.isPresent()){
            teamMemberService.deleteById(id);
        }
        return "redirect:/admin?msg=Member was removed";
    }
}
