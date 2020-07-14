package br.com.souzabrunoj.challenge_catho.widgets

import android.content.Context
import android.os.Build
import android.view.View
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import assertk.assertThat
import assertk.assertions.isEqualTo
import br.com.souzabrunoj.challenge_catho.R
import kotlinx.android.synthetic.main.widget_text_view.view.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.stopKoin
import org.robolectric.Robolectric
import org.robolectric.android.AttributeSetBuilder
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O])
@RunWith(AndroidJUnit4::class)
class TextViewWidgetTest {

    private lateinit var context: Context
    private lateinit var attrs: AttributeSetBuilder

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        attrs = Robolectric.buildAttributeSet()
    }

    @After
    fun cleanUp(){
        stopKoin()
    }

    @Test
    fun `when instance with default attribute is correctly`() {
        with(TextViewWidget(context, attrs.build())) {
            assertThat(text).isEqualTo(null)
            assertThat(loading).isEqualTo(false)
            assertThat(error).isEqualTo(false)
        }
    }

    @Test
    fun `verify containers visibility by default`() {
        with(TextViewWidget(context, attrs.build())) {
            assertThat(tv_text.visibility).isEqualTo(View.VISIBLE)
            assertThat(container_error.visibility).isEqualTo(View.GONE)
            assertThat(container_loading.visibility).isEqualTo(View.GONE)
        }
    }

    @Test
    fun `error container error should be gone by default`() {
        with(TextViewWidget(context, attrs.build())) {
            assertThat(container_error.visibility).isEqualTo(View.GONE)
        }
    }

    @Test
    fun `loading state should be gone by default`() {
        with(TextViewWidget(context, attrs.build())) {
            assertThat(container_loading.visibility).isEqualTo(View.GONE)
        }
    }

    @Test
    fun `when set loading state view container and error container should be gone`() {
        val view = TextViewWidget(context, attrs.build())
        view.loading = true
        with(view) {
            assertThat(container_loading.visibility).isEqualTo(View.VISIBLE)
            assertThat(tv_text.visibility).isEqualTo(View.GONE)
            assertThat(container_error.visibility).isEqualTo(View.GONE)
        }
    }

    @Test
    fun `when set error state view container and loading container should be gone`() {
        val view = TextViewWidget(context, attrs.build())
        view.error = true
        with(view) {
            assertThat(container_error.visibility).isEqualTo(View.VISIBLE)
            assertThat(container_loading.visibility).isEqualTo(View.GONE)
            assertThat(tv_text.visibility).isEqualTo(View.GONE)
        }
    }

    @Test
    fun `verify attribute text when it is initiated`() {
        attrs.addAttribute(R.attr.text, "Text")
        with(TextViewWidget(context, attrs.build())) {
            assertThat(tv_text.text).isEqualTo("Text")
        }
    }
}