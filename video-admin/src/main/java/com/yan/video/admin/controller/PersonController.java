package com.yan.video.admin.controller;

import com.yan.video.common.ResponseResult;
import com.yan.video.admin.dto.AddPersonParam;
import com.yan.video.admin.dto.PersonRoleDto;
import com.yan.video.admin.dto.UpdatePersonParam;
import com.yan.video.admin.service.PersonService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author likaiyan
 * @date 2020/6/10 6:07 下午
 */
@Api(tags = "personController", description = "人员信息接口")
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/add")
    public ResponseResult addPerson(@Valid @RequestBody AddPersonParam addPersonParam, BindingResult result) {
        return ResponseResult.success(personService.addPerson(addPersonParam));
    }

    @PostMapping("/delete")
    public ResponseResult deletePerson(@RequestBody Integer personId) {
        personService.deletePerson(personId);
        return ResponseResult.success(null);
    }

    @GetMapping("/list/all")
    public ResponseResult getAllPerson() {
        return ResponseResult.success(personService.getAllPerson());
    }

    @GetMapping("/list/search")
    public ResponseResult searchPerson(@RequestParam(required = false) String keyword,
                                    @RequestParam(defaultValue = "1") Integer pageNum,
                                    @RequestParam(defaultValue = "5") Integer pageSize) {
        return ResponseResult.success(personService.getPersons(keyword, pageNum, pageSize));
    }

    @GetMapping("/{personId}")
    public ResponseResult getPerson(@PathVariable Integer personId){
        return ResponseResult.success(personService.getPerson(personId));
    }

    @PostMapping("/update")
    public ResponseResult updatePerson(@Valid @RequestBody UpdatePersonParam updatePersonParam, BindingResult result){
        personService.updatePerson(updatePersonParam);
        return ResponseResult.success(null);
    }

    @PostMapping("/actor/add")
    public ResponseResult addActor(@Valid @RequestBody PersonRoleDto personRoleDto, BindingResult result){
        personService.addActor(personRoleDto);
        return ResponseResult.success(null);
    }

    @PostMapping("/actor/delete")
    public ResponseResult deleteActor(@Valid @RequestBody PersonRoleDto personRoleDto, BindingResult result){
        personService.deleteActor(personRoleDto);
        return ResponseResult.success(null);
    }

    @GetMapping("/actor/{personId}/videogroup")
    public ResponseResult getVideoGroupByActor(@PathVariable Integer personId){
        return ResponseResult.success(personService.getVideoGroupByActor(personId));
    }

    @PostMapping("/writer/add")
    public ResponseResult addWriter(@Valid @RequestBody PersonRoleDto personRoleDto, BindingResult result){
        personService.addWriter(personRoleDto);
        return ResponseResult.success(null);
    }

    @PostMapping("/writer/delete")
    public ResponseResult deleteWriter(@Valid @RequestBody PersonRoleDto personRoleDto, BindingResult result){
        personService.deleteWriter(personRoleDto);
        return ResponseResult.success(null);
    }

    @GetMapping("/writer/{personId}/videogroup")
    public ResponseResult getVideoGroupByWriter(@PathVariable Integer personId){
        return ResponseResult.success(personService.getVideoGroupByWriter(personId));
    }

    @PostMapping("/director/add")
    public ResponseResult addDirector(@Valid @RequestBody PersonRoleDto personRoleDto, BindingResult result){
        personService.addDirector(personRoleDto);
        return ResponseResult.success(null);
    }

    @PostMapping("/director/delete")
    public ResponseResult deleteDirector(@Valid @RequestBody PersonRoleDto personRoleDto, BindingResult result){
        personService.deleteDirector(personRoleDto);
        return ResponseResult.success(null);
    }

    @GetMapping("/director/{personId}/videogroup")
    public ResponseResult getVideoGroupByDirector(@PathVariable Integer personId){
        return ResponseResult.success(personService.getVideoGroupByDirector(personId));
    }

}
