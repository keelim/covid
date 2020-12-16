package com.keelim.coronakorea.ui.update

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.google.android.play.core.review.ReviewManagerFactory
import com.keelim.coronakorea.R

class UpdateFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.settings_preferences)
        readyReview()
    }

    override fun onPreferenceTreeClick(preference: Preference): Boolean { //preference 클릭 리스너
        when (preference.key) {
            "update" -> {
                Toast.makeText(context, "인 앱 업데이트 준비 중입니다.", Toast.LENGTH_SHORT).show()
            }

            "mail" -> {
                Intent(Intent.ACTION_SEND).apply {
                    type = "plain/Text"
                    putExtra(Intent.EXTRA_EMAIL, "kimh00335@gmail.com")
                    putExtra(Intent.EXTRA_SUBJECT, "[cnuBus] 문의 사항")
                    startActivity(this)
                }
            }
        }
        return super.onPreferenceTreeClick(preference)
    }


    private fun readyReview() {
        val manager = ReviewManagerFactory.create(requireActivity())

        manager.requestReviewFlow().apply {
            addOnCompleteListener {
                if (this.isSuccessful) {
                    manager.launchReviewFlow(requireActivity(), this.result)
                }
            }
        }
    }

}