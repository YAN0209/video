package com.yan.video.admin.controller;

import com.yan.video.common.ResponseResult;
import com.yan.video.admin.dto.*;
import com.yan.video.admin.service.PersonService;
import com.yan.video.admin.service.VideoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

/**
 * @author likaiyan
 * @date 2020/6/10 3:17 下午
 */
@Api(tags = "videoController", description = "视频相关接口")
@RestController
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private PersonService personService;

    @PostMapping("/videogroup/add")
    public ResponseResult addVideoGroup(@Valid @RequestBody AddVideoGroupParam videoGroup, BindingResult result) {
        return ResponseResult.success(videoService.addVideoGroup(videoGroup));
    }

    @PostMapping("/videogroup/update")
    public ResponseResult updateVideoGroup(@Valid @RequestBody UpdateVideoGroupParam videoGroup, BindingResult result) {
        videoService.updateVideoGroup(videoGroup);
        return ResponseResult.success(null);
    }

    @GetMapping("/videogroup/list/all")
    public ResponseResult getAllVideoGroup() {
        return ResponseResult.success(videoService.getAllVideoGroups());
    }

    @GetMapping("/videogroup/list/search")
    public ResponseResult searchVideoGroup(@RequestParam String keyword,
                                           @RequestParam(defaultValue = "1") Integer pageNum,
                                           @RequestParam(defaultValue = "5") Integer pageSize) {
        return ResponseResult.success(videoService.getVideoGroups(keyword, pageNum, pageSize));
    }

    @GetMapping("/videogroup/{groupId}")
    public ResponseResult getVideoGroup(@PathVariable("groupId") Integer groupId) {
        return ResponseResult.success(videoService.getVideoGroup(groupId));
    }

    @PostMapping("/videogroup/delete")
    public ResponseResult deleteVideoGroup(@RequestBody Integer groupId) {
        videoService.deleteVideoGroup(groupId);
        return ResponseResult.success(null);
    }

    @PostMapping("/video/add")
    public ResponseResult addVideo(@Valid @RequestBody AddVideoParam video, BindingResult result) {
        return ResponseResult.success(videoService.addVideo(video));
    }

    @PostMapping("/video/update")
    public ResponseResult updateVideo(@Valid @RequestBody UpdateVideoParam video, BindingResult result) {
        videoService.updateVideo(video);
        return ResponseResult.success(null);
    }

    @GetMapping("/videogroup/{groupId}/video")
    public ResponseResult getVideos(@PathVariable("groupId") Integer groupId,
                                    @RequestParam(defaultValue = "1") Integer pageNum,
                                    @RequestParam(defaultValue = "5") Integer pageSize) {
        return ResponseResult.success(videoService.getVideos(groupId, pageNum, pageSize));
    }

    @GetMapping("/video/{videoId}")
    public ResponseResult getVideo(@PathVariable("videoId") Integer videoId) {
        return ResponseResult.success(videoService.getVideo(videoId));
    }

    @PostMapping("/video/delete")
    public ResponseResult deleteVideo(@NotEmpty @RequestBody Integer videoId) {
        videoService.deleteVideo(videoId);
        return ResponseResult.success(null);
    }

    @GetMapping("/videogroup/{groupId}/actor")
    public ResponseResult getActorByGroupId(@PathVariable Integer groupId) {
        return ResponseResult.success(personService.getActorByVideoGroupId(groupId));
    }

    @GetMapping("/videogroup/{groupId}/writer")
    public ResponseResult getWriterByGroupId(@PathVariable Integer groupId) {
        return ResponseResult.success(personService.getWriterByVideoGroupId(groupId));
    }

    @GetMapping("/videogroup/{groupId}/director")
    public ResponseResult getDirectorByGroupId(@PathVariable Integer groupId) {
        return ResponseResult.success(personService.getDirectorByVideoGroupId(groupId));
    }

    @PostMapping("/videotype/add")
    public ResponseResult addVideoType(@Valid @RequestBody AddVideoTypeParam addVideoTypeParam, BindingResult result) {
        return ResponseResult.success(videoService.addVideoType(addVideoTypeParam));
    }

    @PostMapping("/videotype/update")
    public ResponseResult updateVideoType(@Valid @RequestBody UpdateVideoTypeParam updateVideoTypeParam, BindingResult result) {
        videoService.updateVideoType(updateVideoTypeParam);
        return ResponseResult.success(null);
    }

    @PostMapping("/videotype/delete")
    public ResponseResult deleteVideoType(@RequestBody Integer typeId) {
        videoService.deleteVideoType(typeId);
        return ResponseResult.success(null);
    }

    @GetMapping("/videotype/list/all")
    public ResponseResult getAllVideoType() {
        return ResponseResult.success(videoService.getAllVideoType());
    }

    @GetMapping("/videotype/list/search")
    public ResponseResult searchVideoType(@RequestParam String keyword,
                                          @RequestParam(defaultValue = "1") Integer pageNum,
                                          @RequestParam(defaultValue = "5") Integer pageSize) {
        return ResponseResult.success(videoService.getVideoTypes(keyword, pageNum, pageSize));
    }

    @GetMapping("/videotype/{typeId}")
    public ResponseResult getVideoType(@PathVariable Integer typeId) {
        return ResponseResult.success(videoService.getVideoType(typeId));
    }

    @GetMapping("/videogroup/{groupId}/videotype")
    public ResponseResult getVideoTypeByGroupId(@PathVariable Integer groupId) {
        return ResponseResult.success(videoService.getVideoTypeByGroupId(groupId));
    }

    @PostMapping("/videotag/add")
    public ResponseResult addVideoTag(@Valid @RequestBody AddVideoTagParam addVideoTagParam, BindingResult result) {
        return ResponseResult.success(videoService.addVideoTag(addVideoTagParam));
    }

    @PostMapping("/videotag/update")
    public ResponseResult updateVideoTag(@Valid @RequestBody UpdateVideoTagParam updateVideoTagParam, BindingResult result) {
        videoService.updateVideoTag(updateVideoTagParam);
        return ResponseResult.success(null);
    }

    @PostMapping("/videotag/delete")
    public ResponseResult deleteVideoTag(@RequestBody Integer typeId) {
        videoService.deleteVideoTag(typeId);
        return ResponseResult.success(null);
    }

    @GetMapping("/videotag/list/all")
    public ResponseResult getAllVideoTag() {
        return ResponseResult.success(videoService.getAllVideoTag());
    }

    @GetMapping("/videotag/list/search")
    public ResponseResult searchVideoTag(@RequestParam String keyword,
                                         @RequestParam(defaultValue = "1") Integer pageNum,
                                         @RequestParam(defaultValue = "5") Integer pageSize) {
        return ResponseResult.success(videoService.getVideoTags(keyword, pageNum, pageSize));
    }

    @GetMapping("/videotag/{typeId}")
    public ResponseResult getVideoTag(@PathVariable Integer typeId) {
        return ResponseResult.success(videoService.getVideoTag(typeId));
    }

    @GetMapping("/videogroup/{groupId}/videotag")
    public ResponseResult getVideoTagByGroupId(@PathVariable Integer groupId) {
        return ResponseResult.success(videoService.getVideoTagByGroupId(groupId));
    }

    @PostMapping("/videogroup/videotype/add")
    public ResponseResult addVideoGroupType(@Valid @RequestBody VideoGroupRelateDto videoGroupRelateDto, BindingResult result){
        videoService.addVideoGroupType(videoGroupRelateDto);
        return ResponseResult.success(null);
    }

    @PostMapping("/videogroup/videotype/delete")
    public ResponseResult deleteVideoGroupType(@Valid @RequestBody VideoGroupRelateDto videoGroupRelateDto, BindingResult result){
        videoService.deleteVideoGroupType(videoGroupRelateDto);
        return ResponseResult.success(null);
    }

    @PostMapping("/videogroup/videotag/add")
    public ResponseResult addVideoGroupTag(@Valid @RequestBody VideoGroupRelateDto videoGroupRelateDto, BindingResult result){
        videoService.addVideoGroupTag(videoGroupRelateDto);
        return ResponseResult.success(null);
    }

    @PostMapping("/videogroup/videotag/delete")
    public ResponseResult deleteVideoGroupTag(@Valid @RequestBody VideoGroupRelateDto videoGroupRelateDto, BindingResult result){
        videoService.deleteVideoGroupTag(videoGroupRelateDto);
        return ResponseResult.success(null);
    }

}
