package com.juul.pommel.test

import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.SourceFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.Assert.assertEquals
import kotlin.test.Test

class PommelProcessorClassTests : PommelProcessorTests() {

    @Test
    fun `unscoped constructor injection`() {
        val result = compile(
            SourceFile.kotlin(
                "source.kt",
                """
          package test 
          
          import com.juul.pommel.annotations.SoloModule
          import javax.inject.Inject 
          
          @SoloModule
          class SampleClass @Inject constructor()

          """
            )
        )

        assertEquals(result.exitCode, KotlinCompilation.ExitCode.OK)
        val file = result.getGeneratedFile("SampleClass_SoloModule.java")
        assertThat(file).isEqualToJava(
            """
         package test;
         
         import dagger.Module;
         import dagger.Provides;
         import dagger.hilt.InstallIn;
         import dagger.hilt.android.components.ApplicationComponent;
         import javax.annotation.Generated;
         
         $GENERATED_ANNOTATION
         @Module
         @InstallIn(ApplicationComponent.class)
         public abstract class SampleClass_SoloModule {
           @Provides
           public static SampleClass provides_test_SampleClass() {
             return new SampleClass(
                 );
           }
         }"""
        )
    }

    @Test
    fun `install is false`() {
        val result = compile(
            SourceFile.kotlin(
                "source.kt",
                """
          package test 
          
          import com.juul.pommel.annotations.SoloModule
          import javax.inject.Inject 
          
          @SoloModule(install = false)
          class SampleClass @Inject constructor()

          """
            )
        )

        assertEquals(result.exitCode, KotlinCompilation.ExitCode.OK)
        val file = result.getGeneratedFile("SampleClass_SoloModule.java")
        assertThat(file).isEqualToJava(
            """
         package test;
         
         import dagger.Module;
         import dagger.Provides;
         import javax.annotation.Generated;
         
         $GENERATED_ANNOTATION
         @Module
         public abstract class SampleClass_SoloModule {
           @Provides
           public static SampleClass provides_test_SampleClass() {
             return new SampleClass(
                 );
           }
         }"""
        )
    }

    @Test
    fun `install in Singleton scope `() {
        val result = compile(
            SourceFile.kotlin(
                "source.kt",
                """
          package test 
          
          import com.juul.pommel.annotations.SoloModule
          import javax.inject.Inject
          import javax.inject.Singleton 
          
          @SoloModule
          @Singleton
          class SampleClass @Inject constructor()

          """
            )
        )

        assertEquals(result.exitCode, KotlinCompilation.ExitCode.OK)
        val file = result.getGeneratedFile("SampleClass_SoloModule.java")
        assertThat(file).isEqualToJava(
            """
         package test;

         import dagger.Module;
         import dagger.Provides;
         import dagger.hilt.InstallIn;
         import dagger.hilt.android.components.ApplicationComponent;
         import javax.annotation.Generated;
         import javax.inject.Singleton;
         
         $GENERATED_ANNOTATION
         @Module
         @InstallIn(ApplicationComponent.class)
         public abstract class SampleClass_SoloModule {
           @Provides
           @Singleton
           public static SampleClass provides_test_SampleClass() {
             return new SampleClass(
                 );
           }
         }"""
        )
    }

    @Test
    fun `install in ActivityRetainedScoped`() {
        val result = compile(
            SourceFile.kotlin(
                "source.kt",
                """
          package test 
          
          import com.juul.pommel.annotations.SoloModule
          import javax.inject.Inject
          import dagger.hilt.android.scopes.ActivityRetainedScoped
          
          @SoloModule
          @ActivityRetainedScoped
          class SampleClass @Inject constructor()

          """
            )
        )

        assertEquals(result.exitCode, KotlinCompilation.ExitCode.OK)
        val file = result.getGeneratedFile("SampleClass_SoloModule.java")
        assertThat(file).isEqualToJava(
            """
         package test;

         import dagger.Module;
         import dagger.Provides;
         import dagger.hilt.InstallIn;
         import dagger.hilt.android.components.ActivityRetainedComponent;
         import dagger.hilt.android.scopes.ActivityRetainedScoped;
         import javax.annotation.Generated;
         
         $GENERATED_ANNOTATION
         @Module
         @InstallIn(ActivityRetainedComponent.class)
         public abstract class SampleClass_SoloModule {
           @Provides
           @ActivityRetainedScoped
           public static SampleClass provides_test_SampleClass() {
             return new SampleClass(
                 );
           }
         }"""
        )
    }

    @Test
    fun `install in ActivityScoped`() {
        val result = compile(
            SourceFile.kotlin(
                "source.kt",
                """
          package test 
          
          import com.juul.pommel.annotations.SoloModule
          import javax.inject.Inject
          import dagger.hilt.android.scopes.ActivityScoped
          
          @SoloModule
          @ActivityScoped
          class SampleClass @Inject constructor()

          """
            )
        )

        assertEquals(result.exitCode, KotlinCompilation.ExitCode.OK)
        val file = result.getGeneratedFile("SampleClass_SoloModule.java")
        assertThat(file).isEqualToJava(
            """
         package test;

         import dagger.Module;
         import dagger.Provides;
         import dagger.hilt.InstallIn;
         import dagger.hilt.android.components.ActivityComponent;
         import dagger.hilt.android.scopes.ActivityScoped;
         import javax.annotation.Generated;
         
         $GENERATED_ANNOTATION
         @Module
         @InstallIn(ActivityComponent.class)
         public abstract class SampleClass_SoloModule {
           @Provides
           @ActivityScoped
           public static SampleClass provides_test_SampleClass() {
             return new SampleClass(
                 );
           }
         }"""
        )
    }

    @Test
    fun `install in FragmentScoped`() {
        val result = compile(
            SourceFile.kotlin(
                "source.kt",
                """
          package test 
          
          import com.juul.pommel.annotations.SoloModule
          import javax.inject.Inject
          import dagger.hilt.android.scopes.FragmentScoped
          
          @SoloModule
          @FragmentScoped
          class SampleClass @Inject constructor()

          """
            )
        )

        assertEquals(result.exitCode, KotlinCompilation.ExitCode.OK)
        val file = result.getGeneratedFile("SampleClass_SoloModule.java")
        assertThat(file).isEqualToJava(
            """
         package test;

         import dagger.Module;
         import dagger.Provides;
         import dagger.hilt.InstallIn;
         import dagger.hilt.android.components.FragmentComponent;
         import dagger.hilt.android.scopes.FragmentScoped;
         import javax.annotation.Generated;
         
         $GENERATED_ANNOTATION
         @Module
         @InstallIn(FragmentComponent.class)
         public abstract class SampleClass_SoloModule {
           @Provides
           @FragmentScoped
           public static SampleClass provides_test_SampleClass() {
             return new SampleClass(
                 );
           }
         }"""
        )
    }

    @Test
    fun `install in ServiceScoped`() {
        val result = compile(
            SourceFile.kotlin(
                "source.kt",
                """
          package test 
          
          import com.juul.pommel.annotations.SoloModule
          import javax.inject.Inject
          import dagger.hilt.android.scopes.ServiceScoped
          
          @SoloModule
          @ServiceScoped
          class SampleClass @Inject constructor()

          """
            )
        )

        assertEquals(result.exitCode, KotlinCompilation.ExitCode.OK)
        val file = result.getGeneratedFile("SampleClass_SoloModule.java")
        assertThat(file).isEqualToJava(
            """
         package test;

         import dagger.Module;
         import dagger.Provides;
         import dagger.hilt.InstallIn;
         import dagger.hilt.android.components.ServiceComponent;
         import dagger.hilt.android.scopes.ServiceScoped;
         import javax.annotation.Generated;
         
         $GENERATED_ANNOTATION
         @Module
         @InstallIn(ServiceComponent.class)
         public abstract class SampleClass_SoloModule {
           @Provides
           @ServiceScoped
           public static SampleClass provides_test_SampleClass() {
             return new SampleClass(
                 );
           }
         }"""
        )
    }

    @Test
    fun `install in ViewScoped`() {
        val result = compile(
            SourceFile.kotlin(
                "source.kt",
                """
          package test 
          
          import com.juul.pommel.annotations.SoloModule
          import javax.inject.Inject
          import dagger.hilt.android.scopes.ViewScoped
          
          @SoloModule
          @ViewScoped
          class SampleClass @Inject constructor()

          """
            )
        )

        assertEquals(result.exitCode, KotlinCompilation.ExitCode.OK)
        val file = result.getGeneratedFile("SampleClass_SoloModule.java")
        assertThat(file).isEqualToJava(
            """
         package test;

         import dagger.Module;
         import dagger.Provides;
         import dagger.hilt.InstallIn;
         import dagger.hilt.android.components.ViewComponent;
         import dagger.hilt.android.scopes.ViewScoped;
         import javax.annotation.Generated;
         
         $GENERATED_ANNOTATION
         @Module
         @InstallIn(ViewComponent.class)
         public abstract class SampleClass_SoloModule {
           @Provides
           @ViewScoped
           public static SampleClass provides_test_SampleClass() {
             return new SampleClass(
                 );
           }
         }"""
        )
    }

    @Test
    fun `custom scope fails`() {
        val result = compile(
            SourceFile.kotlin(
                "source.kt",
                """
          package test 
          
        
          
          import com.juul.pommel.annotations.SoloModule
          import javax.inject.Inject
          import javax.inject.Scope
          
          @Scope
          annotation class CustomScope
          
          @SoloModule
          @CustomScope
          class SampleClass @Inject constructor()

          """
            )
        )

        assertEquals(result.exitCode, KotlinCompilation.ExitCode.COMPILATION_ERROR)
        assertThat(result.messages).contains("error: @SoloModule does not support custom scopes--use Dagger-Hilt defined scopes or set install to false")
    }

    @Test
    fun `custom scope with install false`() {
        val result = compile(
            SourceFile.kotlin(
                "source.kt",
                """
          package test 
          
        
          
          import com.juul.pommel.annotations.SoloModule
          import javax.inject.Inject
          import javax.inject.Scope
          
          @Scope
          annotation class CustomScope
          
          @SoloModule(install = false)
          @CustomScope
          class SampleClass @Inject constructor()

          """
            )
        )

        assertEquals(result.exitCode, KotlinCompilation.ExitCode.OK)
        val file = result.getGeneratedFile("SampleClass_SoloModule.java")
        assertThat(file).isEqualToJava(
            """
         package test;

         import dagger.Module;
         import dagger.Provides;
         import javax.annotation.Generated;

         $GENERATED_ANNOTATION
         @Module
         public abstract class SampleClass_SoloModule {
           @Provides
           @CustomScope
           public static SampleClass provides_test_SampleClass() {
             return new SampleClass(
                 );
           }
         }"""
        )
    }

    @Test
    fun `with parameters`() {
        val result = compile(
            SourceFile.kotlin(
                "source.kt",
                """
          package test 
          
          import com.juul.pommel.annotations.SoloModule
          import javax.inject.Inject
          import javax.inject.Singleton 
          
          @SoloModule
          @Singleton
          class SampleClass @Inject constructor(
              val a: Int,
              val b: String,
              val c: Double
          )

          """
            )
        )

        assertEquals(result.exitCode, KotlinCompilation.ExitCode.OK)
        val file = result.getGeneratedFile("SampleClass_SoloModule.java")
        assertThat(file).isEqualToJava(
            """
         package test;

         import dagger.Module;
         import dagger.Provides;
         import dagger.hilt.InstallIn;
         import dagger.hilt.android.components.ApplicationComponent;
         import java.lang.String;
         import javax.annotation.Generated;
         import javax.inject.Singleton;
         
         $GENERATED_ANNOTATION
         @Module
         @InstallIn(ApplicationComponent.class)
         public abstract class SampleClass_SoloModule {
           @Provides
           @Singleton
           public static SampleClass provides_test_SampleClass(int a, String b, double c) {
             return new SampleClass(
                 a,
                 b,
                 c);
           }
         }"""
        )
    }

    @Test
    fun `with qualified parameters`() {
        val result = compile(
            SourceFile.kotlin(
                "source.kt",
                """
          package test 
          
          import com.juul.pommel.annotations.SoloModule
          import javax.inject.Inject
          import javax.inject.Named
          import javax.inject.Singleton 
          
          @SoloModule
          @Singleton
          class SampleClass @Inject constructor(
              @Named("a" ) val a: Int,
              val b: String,
              val c: Double,
              @Named("b") val d: Byte
          )

          """
            )
        )

        assertEquals(result.exitCode, KotlinCompilation.ExitCode.OK)
        val file = result.getGeneratedFile("SampleClass_SoloModule.java")
        assertThat(file).isEqualToJava(
            """
         package test;

         import dagger.Module;
         import dagger.Provides;
         import dagger.hilt.InstallIn;
         import dagger.hilt.android.components.ApplicationComponent;
         import java.lang.String;
         import javax.annotation.Generated;
         import javax.inject.Named;
         import javax.inject.Singleton;
         
         $GENERATED_ANNOTATION
         @Module
         @InstallIn(ApplicationComponent.class)
         public abstract class SampleClass_SoloModule {
           @Provides
           @Singleton
           public static SampleClass provides_test_SampleClass(@Named("a") int a, String b, double c,
               @Named("b") byte d) {
             return new SampleClass(
                 a,
                 b,
                 c,
                 d);
           }
         }"""
        )
    }

    @Test
    fun `bind abstract class`() {
        val result = compile(
            SourceFile.kotlin(
                "source.kt",
                """
          package test 
          
          import com.juul.pommel.annotations.SoloModule
          import javax.inject.Inject
          import javax.inject.Named
          import javax.inject.Singleton 
          
          abstract class AbstractClass
          
          @SoloModule(AbstractClass::class)
          @Singleton
          class SampleClass @Inject constructor(
              @Named("a") val a: Int,
              val b: String,
              val c: Double,
              @Named("b") val d: Byte
          ) : AbstractClass()

          """
            )
        )

        assertEquals(result.exitCode, KotlinCompilation.ExitCode.OK)
        val file = result.getGeneratedFile("SampleClass_SoloModule.java")
        assertThat(file).isEqualToJava(
            """
         package test;

         import dagger.Binds;
         import dagger.Module;
         import dagger.hilt.InstallIn;
         import dagger.hilt.android.components.ApplicationComponent;
         import javax.annotation.Generated;
         import javax.inject.Singleton;

         $GENERATED_ANNOTATION
         @Module
         @InstallIn(ApplicationComponent.class)
         public abstract class SampleClass_SoloModule {
           @Binds
           @Singleton
           public abstract AbstractClass binds_test_SampleClass(SampleClass sampleClass);
         }"""
        )
    }

    @Test
    fun `bind interface`() {
        val result = compile(
            SourceFile.kotlin(
                "source.kt",
                """
          package test 
          
          import com.juul.pommel.annotations.SoloModule
          import javax.inject.Inject
          import javax.inject.Named
          import javax.inject.Singleton 
          
          interface TestInterface
          
          @SoloModule(TestInterface::class)
          @Singleton
          class SampleClass @Inject constructor(
              @Named("a" ) val a: Int,
              val b: String,
              val c: Double,
              @Named("b") val d: Byte
          ) : TestInterface

          """
            )
        )

        assertEquals(result.exitCode, KotlinCompilation.ExitCode.OK)
        val file = result.getGeneratedFile("SampleClass_SoloModule.java")
        assertThat(file).isEqualToJava(
            """
         package test;

         import dagger.Binds;
         import dagger.Module;
         import dagger.hilt.InstallIn;
         import dagger.hilt.android.components.ApplicationComponent;
         import javax.annotation.Generated;
         import javax.inject.Singleton;
         
         $GENERATED_ANNOTATION
         @Module
         @InstallIn(ApplicationComponent.class)
         public abstract class SampleClass_SoloModule {
           @Binds
           @Singleton
           public abstract TestInterface binds_test_SampleClass(SampleClass sampleClass);
         }"""
        )
    }

    @Test
    fun `specify binding interface`() {
        val result = compile(
            SourceFile.kotlin(
                "source.kt",
                """
          package test 
          
          import com.juul.pommel.annotations.SoloModule
          import javax.inject.Inject
          import javax.inject.Named
          import javax.inject.Singleton 
          
          interface TestInterface
          
          interface SecondTestInterface
          
          @SoloModule(SecondTestInterface::class)
          @Singleton
          class SampleClass @Inject constructor(
              @Named("a" ) val a: Int,
              val b: String,
              val c: Double,
              @Named("b") val d: Byte
          ) : TestInterface, SecondTestInterface

          """
            )
        )

        assertEquals(result.exitCode, KotlinCompilation.ExitCode.OK)
        val file = result.getGeneratedFile("SampleClass_SoloModule.java")
        assertThat(file).isEqualToJava(
            """
         package test;

         import dagger.Binds;
         import dagger.Module;
         import dagger.hilt.InstallIn;
         import dagger.hilt.android.components.ApplicationComponent;
         import javax.annotation.Generated;
         import javax.inject.Singleton;
         
         $GENERATED_ANNOTATION
         @Module
         @InstallIn(ApplicationComponent.class)
         public abstract class SampleClass_SoloModule {
           @Binds
           @Singleton
           public abstract SecondTestInterface binds_test_SampleClass(SampleClass sampleClass);
         }"""
        )
    }

    @Test
    fun `do not bind interface`() {
        val result = compile(
            SourceFile.kotlin(
                "source.kt",
                """
          package test 
          
          import com.juul.pommel.annotations.SoloModule
          import javax.inject.Inject
          import javax.inject.Named
          import javax.inject.Singleton 
          
          interface TestInterface
          
          @SoloModule
          @Singleton
          class SampleClass @Inject constructor(
              @Named("a" ) val a: Int,
              val b: String,
              val c: Double,
              @Named("b") val d: Byte
          ) : TestInterface

          """
            )
        )

        assertEquals(result.exitCode, KotlinCompilation.ExitCode.OK)
        val file = result.getGeneratedFile("SampleClass_SoloModule.java")
        assertThat(file).isEqualToJava(
            """
         package test;

         import dagger.Module;
         import dagger.Provides;
         import dagger.hilt.InstallIn;
         import dagger.hilt.android.components.ApplicationComponent;
         import java.lang.String;
         import javax.annotation.Generated;
         import javax.inject.Named;
         import javax.inject.Singleton;
         
         $GENERATED_ANNOTATION
         @Module
         @InstallIn(ApplicationComponent.class)
         public abstract class SampleClass_SoloModule {
           @Provides
           @Singleton
           public static SampleClass provides_test_SampleClass(@Named("a") int a, String b, double c,
               @Named("b") byte d) {
             return new SampleClass(
                 a,
                 b,
                 c,
                 d);
           }
         }"""
        )
    }

    @Test
    fun `bind concrete implementation extending multiple interfaces`() {
        val result = compile(
            SourceFile.kotlin(
                "source.kt",
                """
          package test 
          
          import com.juul.pommel.annotations.SoloModule
          import javax.inject.Inject
          import javax.inject.Named
          import javax.inject.Singleton 
          
          interface TestInterface
          
          interface OtherInterface
          
          @SoloModule
          @Singleton
          class SampleClass @Inject constructor(
              @Named("a" ) val a: Int,
              val b: String,
              val c: Double,
              @Named("b") val d: Byte
          ) : TestInterface, OtherInterface

          """
            )
        )

        assertEquals(result.exitCode, KotlinCompilation.ExitCode.OK)
        val file = result.getGeneratedFile("SampleClass_SoloModule.java")
        assertThat(file).isEqualToJava(
            """
         package test;

         import dagger.Module;
         import dagger.Provides;
         import dagger.hilt.InstallIn;
         import dagger.hilt.android.components.ApplicationComponent;
         import java.lang.String;
         import javax.annotation.Generated;
         import javax.inject.Named;
         import javax.inject.Singleton;
         
         $GENERATED_ANNOTATION
         @Module
         @InstallIn(ApplicationComponent.class)
         public abstract class SampleClass_SoloModule {
           @Provides
           @Singleton
           public static SampleClass provides_test_SampleClass(@Named("a") int a, String b, double c,
               @Named("b") byte d) {
             return new SampleClass(
                 a,
                 b,
                 c,
                 d);
           }
         }"""
        )
    }

    @Test
    fun `bind concrete implementation extending interfaces and abstract class`() {
        val result = compile(
            SourceFile.kotlin(
                "source.kt",
                """
          package test 
          
          import com.juul.pommel.annotations.SoloModule
          import javax.inject.Inject
          import javax.inject.Named
          import javax.inject.Singleton 
          
          interface TestInterface
          
          abstract class AbstractClass
          
          @SoloModule
          @Singleton
          class SampleClass @Inject constructor(
              @Named("a" ) val a: Int,
              val b: String,
              val c: Double,
              @Named("b") val d: Byte
          ) : AbstractClass(), TestInterface

          """
            )
        )

        assertEquals(result.exitCode, KotlinCompilation.ExitCode.OK)
        val file = result.getGeneratedFile("SampleClass_SoloModule.java")
        assertThat(file).isEqualToJava(
            """
         package test;

         import dagger.Module;
         import dagger.Provides;
         import dagger.hilt.InstallIn;
         import dagger.hilt.android.components.ApplicationComponent;
         import java.lang.String;
         import javax.annotation.Generated;
         import javax.inject.Named;
         import javax.inject.Singleton;
         
         $GENERATED_ANNOTATION
         @Module
         @InstallIn(ApplicationComponent.class)
         public abstract class SampleClass_SoloModule {
           @Provides
           @Singleton
           public static SampleClass provides_test_SampleClass(@Named("a") int a, String b, double c,
               @Named("b") byte d) {
             return new SampleClass(
                 a,
                 b,
                 c,
                 d);
           }
         }"""
        )
    }

    @Test
    fun `use abstract class when extending interfaces and abstract class`() {
        val result = compile(
            SourceFile.kotlin(
                "source.kt",
                """
          package test 
          
          import com.juul.pommel.annotations.SoloModule
          import javax.inject.Inject
          import javax.inject.Named
          import javax.inject.Singleton 
          
          interface TestInterface
          
          abstract class AbstractClass
          
          @SoloModule(AbstractClass::class)
          @Singleton
          class SampleClass @Inject constructor(
              @Named("a" ) val a: Int,
              val b: String,
              val c: Double,
              @Named("b") val d: Byte
          ) : AbstractClass(), TestInterface

          """
            )
        )

        assertEquals(result.exitCode, KotlinCompilation.ExitCode.OK)
        val file = result.getGeneratedFile("SampleClass_SoloModule.java")
        assertThat(file).isEqualToJava(
            """
         package test;

         import dagger.Binds;
         import dagger.Module;
         import dagger.hilt.InstallIn;
         import dagger.hilt.android.components.ApplicationComponent;
         import javax.annotation.Generated;
         import javax.inject.Singleton;
         
         $GENERATED_ANNOTATION
         @Module
         @InstallIn(ApplicationComponent.class)
         public abstract class SampleClass_SoloModule {
           @Binds
           @Singleton
           public abstract AbstractClass binds_test_SampleClass(SampleClass sampleClass);
         }"""
        )
    }

    @Test
    fun `private class fails with compilation error`() {
        val result = compile(
            SourceFile.kotlin(
                "source.kt",
                """
          package test 
          
          import com.juul.pommel.annotations.SoloModule
          import javax.inject.Inject 
          
          @SoloModule
          private class SampleClass @Inject constructor()

          """
            )
        )

        assertEquals(result.exitCode, KotlinCompilation.ExitCode.COMPILATION_ERROR)
        assertThat(result.messages).contains("error: Types marked with @SoloModule must be public")
    }

    @Test
    fun `nested non-static class fails with compilation error`() {
        val result = compile(
            SourceFile.kotlin(
                "source.kt",
                """
          package test 
          
          import com.juul.pommel.annotations.SoloModule
          import javax.inject.Inject 
          
          class SampleClass {
              
              @SoloModule
              inner class InnerClass @Inject constructor()
          }

          """
            )
        )

        assertEquals(result.exitCode, KotlinCompilation.ExitCode.COMPILATION_ERROR)
        assertThat(result.messages).contains("error: Nested types marked with @SoloModule must be static")
    }

    @Test
    fun `nested static class`() {
        val result = compile(
            SourceFile.kotlin(
                "source.kt",
                """
          package test 
          
          import com.juul.pommel.annotations.SoloModule
          import javax.inject.Inject 
          
          class SampleClass {
              
              @SoloModule
              class InnerClass @Inject constructor()
          }

          """
            )
        )

        assertEquals(result.exitCode, KotlinCompilation.ExitCode.OK)
        val file = result.getGeneratedFile("SampleClass\$InnerClass_SoloModule.java")
        assertThat(file).isEqualToJava(
            """
         package test;

         import dagger.Module;
         import dagger.Provides;
         import dagger.hilt.InstallIn;
         import dagger.hilt.android.components.ApplicationComponent;
         import javax.annotation.Generated;
         
         $GENERATED_ANNOTATION
         @Module
         @InstallIn(ApplicationComponent.class)
         public abstract class SampleClass${'$'}InnerClass_SoloModule {
           @Provides
           public static SampleClass.InnerClass provides_test_SampleClass${'$'}InnerClass() {
             return new SampleClass.InnerClass(
                 );
           }
         }"""
        )
    }

    @Test
    fun `multiple inject constructor fails`() {
        val result = compile(
            SourceFile.kotlin(
                "source.kt",
                """
          package test 
          
        
          
          import com.juul.pommel.annotations.SoloModule
          import javax.inject.Inject
          import javax.inject.Scope
          
          @Scope
          annotation class CustomScope
          
          @SoloModule
          @CustomScope
          class SampleClass @Inject constructor(private val a: Int) {
              
              @Inject constructor(): this(10) 
          }
          """
            )
        )

        assertEquals(result.exitCode, KotlinCompilation.ExitCode.COMPILATION_ERROR)
        assertThat(result.messages).contains("error: Multiple constructors marked with @Inject annotated found")
        assertThat(result.messages).doesNotContain("An exception occurred: java.lang.IllegalArgumentException: List has more than one element")
    }

    @Test
    fun `bind interface with qualifier`() {
        val result = compile(
            SourceFile.kotlin(
                "source.kt",
                """
          package test 
          
          import com.juul.pommel.annotations.SoloModule
          import javax.inject.Inject
          import javax.inject.Named
          import javax.inject.Singleton 
          
          interface TestInterface
          
          @SoloModule(TestInterface::class)
          @Singleton
          @Named("test")
          class SampleClass @Inject constructor(
              @Named("a" ) val a: Int,
              val b: String,
              val c: Double,
              @Named("b") val d: Byte
          ) : TestInterface

          """
            )
        )

        assertEquals(result.exitCode, KotlinCompilation.ExitCode.OK)
        val file = result.getGeneratedFile("SampleClass_SoloModule.java")
        assertThat(file).isEqualToJava(
            """
         package test;

         import dagger.Binds;
         import dagger.Module;
         import dagger.hilt.InstallIn;
         import dagger.hilt.android.components.ApplicationComponent;
         import javax.annotation.Generated;
         import javax.inject.Named;
         import javax.inject.Singleton;
         
         $GENERATED_ANNOTATION
         @Module
         @InstallIn(ApplicationComponent.class)
         public abstract class SampleClass_SoloModule {
           @Binds
           @Singleton
           @Named("test")
           public abstract TestInterface binds_test_SampleClass(SampleClass sampleClass);
         }"""
        )
    }
}