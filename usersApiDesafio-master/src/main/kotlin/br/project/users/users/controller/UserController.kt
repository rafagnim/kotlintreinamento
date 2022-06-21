package br.project.users.users.controller

import br.project.users.users.entity.User
import br.project.users.users.controller.request.PostUserRequest
import br.project.users.users.security.UserCustomDetails
import br.project.users.users.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("users")
class UserController(
        private val userService: UserService
) {
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAll() : List<User> {
        val user = SecurityContextHolder.getContext().authentication.principal as UserCustomDetails
        val byId = userService.getById(user.id)
        return userService.getAll()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid request : PostUserRequest){
        return userService.save(request)
    }

    @PatchMapping("/{id}/disable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun disable(@PathVariable id : Int){
        return userService.disable(id)
    }

    @PatchMapping("/{id}/enable")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun enable(@PathVariable id : Int){
        return userService.enable(id)
    }

}
