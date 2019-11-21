package com.example

import kotlinx.coroutines.delay
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/")
@SpringBootApplication
class Controller {
	private val logger: Logger = LoggerFactory.getLogger("controller")

	@GetMapping("/ingredients") @ResponseBody
	suspend fun ingredients(@RequestParam(value = "value") value: String): String? {
        val delayMs = delayForMilliseconds()
		val returnedValue = if (listOf("flour", "cinammon", "soda", "sugar", "egg").containsAll(value.split(",")))
			"ok"
		else "not ok"
		return returnedValue.also { logger.info("GET /ingredients?value=$value after $delayMs delay returns $it ") }
	}

	@GetMapping("/heat") @ResponseBody
	suspend fun heat(@RequestParam(value = "value") value: String): String? {
        val delayMs = delayForMilliseconds()
        val returnedValue = if (listOf("butter", "honey").containsAll(value.split(",")))
			"ok"
		else "not ok"
        return returnedValue.also { logger.info("GET /heat?value=$value after $delayMs delay returns $it ") }
	}

	@GetMapping("/dough") @ResponseBody
	suspend fun dough(@RequestParam(value = "value") value: String): String? {
        val delayMs = delayForMilliseconds()
        val returnedValue = if (listOf("flour", "cinammon", "soda", "sugar").containsAll(value.split(",")))
			"ok"
		else "not ok"
        return returnedValue.also { logger.info("GET /dough?value=$value after $delayMs delay returns $it ") }
	}

	@GetMapping("/tray") @ResponseBody
	suspend fun tray(): String? {
        val delayMs = delayForMilliseconds()
        val returnedValue = if ((0..100).random() > 30)
			"ok"
		else "not ok"
        return returnedValue.also { logger.info("GET /tray after $delayMs delay returns $it ") }
	}

	@GetMapping("/icing") @ResponseBody
	suspend fun icing(): String? {
		val delayMs = delayForMilliseconds(5000L) // put 5000L as argument for stress testing
        val returnedValue = if ((0..100).random() > 30)
			"ok"
		else "not ok"
        return returnedValue.also { logger.info("GET /icing after $delayMs delay returns $it ") }
	}

    private suspend fun delayForMilliseconds(fixedAmountOfTime: Long? = 0L): Long {
        val delayTimeMillis = if (fixedAmountOfTime != null && fixedAmountOfTime > 0L)
			fixedAmountOfTime else (0..50L).random()
        delay(delayTimeMillis)
        return delayTimeMillis
    }
}

fun main(args: Array<String>) {
	runApplication<com.example.Controller>(*args)
}


