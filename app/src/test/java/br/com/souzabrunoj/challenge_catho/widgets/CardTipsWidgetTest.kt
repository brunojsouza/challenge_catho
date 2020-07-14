package br.com.souzabrunoj.challenge_catho.widgets

import android.content.Context
import android.os.Build
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.core.content.ContextCompat
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import assertk.assertThat
import assertk.assertions.isEqualTo
import br.com.souzabrunoj.challenge_catho.R
import kotlinx.android.synthetic.main.widget_card_tips.view.*
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
class CardTipsWidgetTest {

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
        with(CardTipsWidget(context, attrs.build())) {
            assertThat(text).isEqualTo(null)
            assertThat(textButton).isEqualTo(null)
            assertThat(likeIcon).isEqualTo(R.drawable.ic_hide_password)
            assertThat(unlikeIcon).isEqualTo(R.drawable.ic_hide_password)
            assertThat(buttonTextColor).isEqualTo(ContextCompat.getColor(context, R.color.white))
            assertThat(buttonBackground).isEqualTo(R.drawable.bg_white_background)
            assertThat(loading).isEqualTo(false)
            assertThat(error).isEqualTo(false)
        }
    }

    @Test
    fun `verify containers visibility by default`() {
        with(CardTipsWidget(context, attrs.build())) {
            assertThat(gp_view_container.visibility).isEqualTo(VISIBLE)
            assertThat(container_error.visibility).isEqualTo(GONE)
            assertThat(sm_container_loading.visibility).isEqualTo(GONE)
        }
    }

    @Test
    fun `error container error should be gone by default`() {
        with(CardTipsWidget(context, attrs.build())) {
            assertThat(container_error.visibility).isEqualTo(GONE)
        }
    }

    @Test
    fun `loading state should be gone by default`() {
        with(CardTipsWidget(context, attrs.build())) {
            assertThat(sm_container_loading.visibility).isEqualTo(GONE)
        }
    }

    @Test
    fun `when set loading state view container and error container should be gone`() {
        val view = CardTipsWidget(context, attrs.build())
        view.loading = true
        with(view) {
            assertThat(sm_container_loading.visibility).isEqualTo(VISIBLE)
            assertThat(gp_view_container.visibility).isEqualTo(GONE)
            assertThat(container_error.visibility).isEqualTo(GONE)
        }
    }

    @Test
    fun `when set error state view container and loading container should be gone`() {
        val view = CardTipsWidget(context, attrs.build())
        view.error = true
        with(view) {
            assertThat(container_error.visibility).isEqualTo(VISIBLE)
            assertThat(sm_container_loading.visibility).isEqualTo(GONE)
            assertThat(gp_view_container.visibility).isEqualTo(GONE)
        }
    }

    @Test
    fun `verify attribute text when it is initiated`() {
        attrs.addAttribute(R.attr.text, "Text")
        with(CardTipsWidget(context, attrs.build())) {
            assertThat(tv_title.text).isEqualTo("Text")

        }
    }

    @Test
    fun `verify attribute button text when it is initiated`() {
        attrs.addAttribute(R.attr.button_text, "Button Text")
        with(CardTipsWidget(context, attrs.build())) {
            assertThat(bt_check_cv.text).isEqualTo("Button Text")
        }
    }
}