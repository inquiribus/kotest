package io.kotest.core.spec.style

import io.kotest.core.config.Project
import io.kotest.core.factory.TestFactory
import io.kotest.core.factory.TestFactoryConfiguration
import io.kotest.core.factory.build
import io.kotest.core.spec.SpecConfiguration
import io.kotest.core.test.TestCaseConfig

/**
 * Creates a [TestFactory] from the given block.
 *
 * The receiver of the block is a [FreeSpecTestFactoryConfiguration] which allows tests
 * to be defined using the 'free-spec' style.
 */
fun freeSpec(block: FreeSpecTestFactoryConfiguration.() -> Unit): TestFactory {
   val config = FreeSpecTestFactoryConfiguration()
   config.block()
   return config.build()
}

class FreeSpecTestFactoryConfiguration : TestFactoryConfiguration(), FreeSpecDsl {
   override fun defaultConfig(): TestCaseConfig = defaultTestCaseConfig ?: Project.testCaseConfig()
   override val addTest = ::addDynamicTest
}

abstract class FreeSpec(body: FreeSpec.() -> Unit = {}) : SpecConfiguration(), FreeSpecDsl {
   override fun defaultConfig(): TestCaseConfig =
      defaultTestCaseConfig ?: defaultTestCaseConfig() ?: Project.testCaseConfig()

   override val addTest = ::addRootTestCase

   init {
      body()
   }
}
