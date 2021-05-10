package pl.bedkowski.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import pl.bedkowski.model.Bar

@RestController
class WebmvcController {

	@GetMapping("/")
	fun greet(): Bar {
		return Bar("hi!")
	}

	@GetMapping("/header")
	fun header(@RequestHeader("x-header") xHeader: String): Bar {
		return Bar(xHeader)
	}
}