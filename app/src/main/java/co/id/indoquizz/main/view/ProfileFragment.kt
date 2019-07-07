package co.id.indoquizz.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.id.indoquizz.R
import co.id.indoquizz.objects.GradeType
import co.id.indoquizz.objects.User
import co.id.indoquizz.objects.adapter.FriendAdapter
import co.id.indoquizz.objects.adapter.ProfileMenuAdapter
import co.id.indoquizz.base.view.BaseFragment
import co.id.indoquizz.utils.image.GlideUtils
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.toolbar_layout_center_title.*

class ProfileFragment : BaseFragment() {

    private val profileMenuAdapter = FastItemAdapter<ProfileMenuAdapter>()
    private val friendListAdapter = FastItemAdapter<FriendAdapter>()
    private val friendRequestAdapter = FastItemAdapter<FriendAdapter>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return getInflate(inflater, R.layout.fragment_profile, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureEmptyToolbarNoHome(view)
        titleToolbar.text = "Profil"
        initProfileHeader()
        initFriendList()
        initFriendRequest()
        initProfileMenu()
    }

    private fun initProfileHeader() {
        GlideUtils.setImageCircle(activity!!, R.drawable.button_about, profilePictureImageView)
        GlideUtils.setImage(activity!!, R.drawable.ic_medal_gold, profileGradeImageView)
    }

    private fun initFriendList() {
        configureHorizontalItemAdapter(friendListAdapter, friendListRecycleView)
        friendListRecycleView.isFocusable = false
        friendListAdapter.clear()
        friendListAdapter.add(FriendAdapter(User("Iron", "mail@gmail.com", GradeType.GOLD)))
        friendListAdapter.add(FriendAdapter(User("Hulk", "mail@gmail.com", GradeType.GOLD)))
        friendListAdapter.add(FriendAdapter(User("Captain", "mail@gmail.com", GradeType.GOLD)))
        friendListAdapter.add(FriendAdapter(User("Doctor", "mail@gmail.com", GradeType.GOLD)))
        friendListAdapter.add(FriendAdapter(User("Thor", "mail@gmail.com", GradeType.GOLD)))
        friendListAdapter.add(FriendAdapter(User("Hawkeye", "mail@gmail.com", GradeType.GOLD)))
        friendListAdapter.add(FriendAdapter(User("Vision", "mail@gmail.com", GradeType.GOLD)))
    }

    private fun initFriendRequest() {
        configureHorizontalItemAdapter(friendRequestAdapter, friendRequestRecycleView)
        friendRequestRecycleView.isFocusable = false
        friendRequestAdapter.clear()
        friendRequestAdapter.add(FriendAdapter(User("Gord", "mail@gmail.com", GradeType.GOLD)))
        friendRequestAdapter.add(FriendAdapter(User("Eudora", "mail@gmail.com", GradeType.GOLD)))
        friendRequestAdapter.add(FriendAdapter(User("Alucard", "mail@gmail.com", GradeType.GOLD)))
        friendRequestAdapter.add(FriendAdapter(User("Harley", "mail@gmail.com", GradeType.GOLD)))
    }

    private fun initProfileMenu() {
        configureGridItemAdapter(profileMenuAdapter, profileMenuRecycleView, 3)
        profileMenuRecycleView.isFocusable = false
        profileMenuAdapter.clear()
        profileMenuAdapter.add(ProfileMenuAdapter(R.drawable.button_medali, "Tingkatan Medal"))
        profileMenuAdapter.add(ProfileMenuAdapter(R.drawable.button_about, "Tentang QuizIndo"))
        profileMenuAdapter.add(ProfileMenuAdapter(R.drawable.button_faq, "FAQ"))
        profileMenuAdapter.add(ProfileMenuAdapter(R.drawable.button_hubungi_admin, "Hubungi Admin"))
        profileMenuAdapter.add(ProfileMenuAdapter(R.drawable.button_share_app, "Bagikan Aplikasi"))
        profileMenuAdapter.add(ProfileMenuAdapter(R.drawable.button_logout, "Keluar"))

    }
}