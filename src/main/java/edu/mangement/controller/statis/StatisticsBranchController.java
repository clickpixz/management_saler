package edu.mangement.controller.statis;

import edu.mangement.entity.sp.InterestMapper;
import edu.mangement.model.BranchDTO;
import edu.mangement.model.form.api.AjaxResponse;
import edu.mangement.service.BranchService;
import edu.mangement.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/statistics")
public class StatisticsBranchController {
    @Autowired
    private BranchService branchService;
    @Autowired
    private ProductService productService;

    @GetMapping("/revenue")
    public String show(Model model) {
        model.addAttribute("tittlePage", "Theo dõi doanh thu chi nhánh");
        model.addAttribute("modelForm", new AjaxResponse());
        initSelectBox(model);
        return "statistics-branch";
    }

    @PostMapping("/interest")
    public String interest(Model model, @ModelAttribute("modelForm") AjaxResponse ajaxResponse) {
        if (ajaxResponse == null || ajaxResponse.getAverage() == null || ajaxResponse.getAverage() < -1 || ajaxResponse.getAverage() == 0) {
            return "redirect:/admin/statistics/revenue";
        }
        var branchId = ajaxResponse.getAverage();
        List<AjaxResponse> list = new ArrayList<>();
        if (branchId != -1) {
            list = productService.calculateInterest(branchId);
        } else if (branchId == -1) {
            var allBranch = branchService.findAllBranch(Pageable.unpaged());
            List<Long> listBranchId = allBranch.stream().map(BranchDTO::getId).collect(Collectors.toList());
            for (int i = 0; i < listBranchId.size(); i++) {
                if(i==0){
                    list.addAll(productService.calculateInterest(listBranchId.get(0)));
                }else {
                    List<AjaxResponse> list1 = productService.calculateInterest(listBranchId.get(i));
                    for (int j = 0; j < 12 ; j++) {
                        AjaxResponse aj1 = list.get(j);
                        AjaxResponse aj2 = list1.get(j);
                        InterestMapper a = aj1.getInterestMapper();
                        InterestMapper b = aj2.getInterestMapper();
                        a.setTotal(a.getTotal().add(b.getTotal()));
                        a.setMemberFee(a.getMemberFee().add(b.getMemberFee()));
                        a.setBranchFee(a.getBranchFee().add(b.getBranchFee()));
                        a.setInterest(a.getInterest().add(b.getInterest()));
                        a.setCapital(a.getCapital().add(b.getCapital()));
                        aj1.setInterestMapper(a);
                        list.set(j,aj1);
                    }
                }
            }
        }
        model.addAttribute("list", list);
        var total = BigDecimal.ZERO;
        for (AjaxResponse ajaxResponse1 : list) {
            total = total.add(ajaxResponse1.getInterestMapper().getTotal());
        }
        var capital = BigDecimal.ZERO;
        for (AjaxResponse ajaxResponse1 : list) {
            capital = capital.add(ajaxResponse1.getInterestMapper().getCapital());
        }
        var interest = BigDecimal.ZERO;
        for (AjaxResponse ajaxResponse1 : list) {
            interest = interest.add(ajaxResponse1.getInterestMapper().getInterest());
        }
        var branchFee = BigDecimal.ZERO;
        for (AjaxResponse ajaxResponse1 : list) {
            branchFee = branchFee.add(ajaxResponse1.getInterestMapper().getBranchFee());
        }
        var memberFee = BigDecimal.ZERO;
        for (AjaxResponse ajaxResponse1 : list) {
            memberFee = memberFee.add(ajaxResponse1.getInterestMapper().getMemberFee());
        }
        var sum = total.subtract(capital).subtract(branchFee).subtract(memberFee);
        model.addAttribute("total", total);
        model.addAttribute("capital", capital);
        model.addAttribute("interest", interest);
        model.addAttribute("branchFee", branchFee);
        model.addAttribute("memberFee", memberFee);
        model.addAttribute("sum", sum);
        model.addAttribute("tittlePage", "Doanh thu chi Nhánh");
        return "statistics-interest";
    }

    private void initSelectBox(Model model) {
        var allBranch = branchService.findAllBranch(Pageable.unpaged());
        model.addAttribute("allBranch", allBranch);
    }
}
