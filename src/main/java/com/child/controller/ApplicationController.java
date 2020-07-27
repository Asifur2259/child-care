package com.child.controller;

import com.child.dto.*;
import com.child.model.Account;
import com.child.service.account.AccountService;
import com.child.service.contactInfo.ContactInfoService;
import com.child.service.packages.PackagesService;
import com.child.service.photo.PhotoService;
import com.child.service.team.TeamMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ApplicationController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private ContactInfoService contactInfoService;
    @Autowired
    private PackagesService packagesService;
    @Autowired
    private TeamMemberService teamMemberService;
    @Autowired
    private PhotoService photoService;

    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request) {
        return "login";
    }

    @GetMapping("/sign-up")
    public String signUp(AccountCreateDto accountCreateDto, Model model) {
        return "register";
    }

    @PostMapping("sign-up")
    public String signUp(@Valid AccountCreateDto accountCreateDto, BindingResult result) throws Exception {
        if (result.hasErrors()) {
            return "sign-up";
        }
        Account account = accountService.createMember(accountCreateDto);
        accountCreateDto.setId(account.getId());
        return "redirect:/verify-code";
    }

    @GetMapping("/verify-code")
    public String verifyCode(Model model, CodeVerifyDto codeVerifyDto) {
        return "verify-code";
    }

    @PostMapping("verify-code")
    public String verifyCodeAction(Model model,@Valid  CodeVerifyDto codeVerifyDto, BindingResult result) {
        if(result.hasErrors()) {
            return "verify-code";
        }
        accountService.verifyCode(codeVerifyDto);
        return "redirect:/login";
    }

    @GetMapping("/")
    public String home(Model model,PackagesDto packagesDto){
        model.addAttribute("packagesDto",this.packagesService.findAll());
        model.addAttribute("photo",this.photoService.findByPath("Home"));
        return "home/index";
    }

    @GetMapping("/contact")
    public String contact(Model model, ContactInfoDto contactInfoDto, MessageDto messageDto){
        model.addAttribute("messageDto",messageDto);
        model.addAttribute("contactInfoDto",this.contactInfoService.findAll());
        return "home/contact";
    }

    @GetMapping("/about")
    public String about(Model model,PackagesDto packagesDto,TeamMemberDto teamMemberDto){
        model.addAttribute("teamMemberDto",this.teamMemberService.findAll());
        model.addAttribute("packagesDto",this.packagesService.findAll());
        model.addAttribute("photo",this.photoService.findByPath("About"));
        return "home/about";
    }

    @GetMapping("/gallery")
    public String gallery(){
        return "home/gallery";
    }

    @GetMapping("/packages")
    public String packages(Model model,PackagesDto packagesDto){
        model.addAttribute("packagesDto",this.packagesService.findAll());
        return "home/packages";
    }
}
