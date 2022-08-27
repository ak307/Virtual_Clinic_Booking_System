package com.vclinic.virtual_clinic_booking_system.controller.user;

import com.vclinic.virtual_clinic_booking_system.model.user.enums.AppUserAppointmentType;
import com.vclinic.virtual_clinic_booking_system.service.user.AppUserService;
import com.vclinic.virtual_clinic_booking_system.model.user.form.AppointmentForm;
import com.vclinic.virtual_clinic_booking_system.model.user.AppointmentType;
import com.vclinic.virtual_clinic_booking_system.model.user.Appointments;
import com.vclinic.virtual_clinic_booking_system.service.email.EmailSender;
import com.vclinic.virtual_clinic_booking_system.service.user.AppointmentService;
import com.vclinic.virtual_clinic_booking_system.service.utils.NavBarAuthInfoChangeHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.Random;

@Controller
@RequestMapping("/virtualAppointment")
public class VirtualAppointmentBookingPageController {
    private final NavBarAuthInfoChangeHelper navBarAuthInfoChangeHelper;
    private final AppUserService appUserService;
    private final AppointmentService appointmentService;
    private final EmailSender emailSender;



    @Autowired
    public VirtualAppointmentBookingPageController(NavBarAuthInfoChangeHelper navBarAuthInfoChangeHelper, AppUserService appUserService, AppointmentService appointmentService, EmailSender emailSender) {
        this.navBarAuthInfoChangeHelper = navBarAuthInfoChangeHelper;
        this.appUserService = appUserService;
        this.appointmentService = appointmentService;
        this.emailSender = emailSender;
    }

    @GetMapping("/booking")
    public String showBookingPage(Model model, Date chooseDate, String fullName,
                                  String phone, String email, String purpose) throws ParseException {
        String template = "virtual_appointment_booking_page.html";
        model.addAttribute("appointment_form", new AppointmentForm(fullName, phone, email, chooseDate, purpose, ""));
        model.addAttribute("availableSlots", appointmentService.getAvailableTimeSlots(chooseDate, AppUserAppointmentType.VIRTUAL.toString()));
        
        return navBarAuthInfoChangeHelper.showNavBarBasedOnUserAuthInfo(model, template);
    }


    

    @PostMapping("/saveAppointment")
    public String saveBooking(@ModelAttribute("appointment_form") @Valid AppointmentForm appointmentForm,
                              BindingResult result, Model model) throws ParseException {

        // execute the following when user change date
        if (appointmentForm.getDescription().equals("-")){
            Date currentDate = appointmentForm.getDate();
            String fullName = appointmentForm.getFullName();
            String phone = appointmentForm.getPhoneNumber();
            String purpose = appointmentForm.getPurpose();
            String email = appointmentForm.getEmail();
            return showBookingPage(model, currentDate, fullName, phone, email, purpose);
        }
            // for normal submit
        if (result.hasErrors()) {
            System.out.println(result);
            return "virtual_appointment_booking_page.html";
        }

        String time = appointmentService.dateFormatConverter12To24Hour(appointmentForm.getTime());
        String fakeLink = fakeUrlGenerator();

        appointmentService.saveAppointment(
                new Appointments(
                        appointmentForm.getPhoneNumber(),
                        appointmentForm.getDate(),
                        LocalTime.parse(time),
                        appointmentForm.getPurpose(),
                        appointmentForm.getDescription(),
                        fakeLink,
                        new AppointmentType(AppUserAppointmentType.VIRTUAL.toString()),
                        appUserService.getUserByEmail(appointmentForm.getEmail())
                )
        );

        Date date = appointmentForm.getDate();
        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        String stringDate = DateFor.format(date);
        String appointmentDatetime = stringDate + " " + time;

        emailSender.send(appointmentForm.getEmail(),
                buildAppointmentInfoEmail(appointmentForm.getFullName(), fakeLink, appointmentDatetime),
                "Appointment confirmed");

        return "redirect:/virtualAppointment/bookingSuccessful";
    }



    private String fakeUrlGenerator(){
        Random random = new Random();
        int start = 100;
        int end = 1000;
        return "http://0.0.0.0:8080/videoConsult/" + random.nextInt(end - start) + start;
    }



    private String buildAppointmentInfoEmail(String name, String link, String datetime) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Your appointment has been confirmed</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Dear " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Your appointment at akVirtualClinic scheduled for " + datetime + " has been confirmed. Please follow the link to consult with doctor at your appointment time.\n"  +
                "Thank you for choosing our clinic.</p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">" + link +"</a> </p></blockquote>\n <p>See you soon.</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }




}
