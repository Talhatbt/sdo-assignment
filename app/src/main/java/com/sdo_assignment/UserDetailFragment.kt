package com.sdo_assignment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sdo_assignment.databinding.FragmentUserDetailBinding
import com.sdo_assignment.model.UserDetailResponse
import com.sdo_assignment.utils.extensions.popStack


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class UserDetailFragment : Fragment() {

    lateinit var userDetail: UserDetailResponse
    private var _binding: FragmentUserDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        binding.user = userDetail
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.toolbar.setNavigationOnClickListener {
            // back button pressed
            popStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(userDetail: UserDetailResponse): UserDetailFragment {
            val fragment = UserDetailFragment()
            fragment.userDetail = userDetail
            return fragment
        }
    }
}