package co.id.indoquizz.main.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import co.id.indoquizz.R
import co.id.indoquizz.objects.GradeType
import co.id.indoquizz.objects.User
import co.id.indoquizz.objects.adapter.UserAdapter
import co.id.indoquizz.base.view.BaseFragment
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter
import kotlinx.android.synthetic.main.fragment_find.*
import kotlinx.android.synthetic.main.toolbar_layout_center_title.*

class FindFragment : BaseFragment() {

    private val userAdapter = FastItemAdapter<UserAdapter>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return getInflate(inflater, R.layout.fragment_find, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configureEmptyToolbarNoHome(view)
        titleToolbar.text = "Cari Teman Sekitar"
        configureItemAdapter(userAdapter, userListRecycleView)
        userListRecycleView.isFocusable = false
        initDummyData()
    }

    private fun initDummyData() {
        userAdapter.clear()
        userAdapter.add(UserAdapter(User("Iron Man", "mail@gmail.com", GradeType.GOLD)))
        userAdapter.add(UserAdapter(User("Captain America", "mail@gmail.com", GradeType.GOLD)))
        userAdapter.add(UserAdapter(User("Thor", "mail@gmail.com", GradeType.SILVER)))
        userAdapter.add(UserAdapter(User("Black Widow", "mail@gmail.com", GradeType.SILVER)))
        userAdapter.add(UserAdapter(User("Hulk", "mail@gmail.com", GradeType.GOLD)))
        userAdapter.add(UserAdapter(User("Hawkeye", "mail@gmail.com", GradeType.BRONZE)))
        userAdapter.add(UserAdapter(User("Vision", "mail@gmail.com", GradeType.BRONZE)))
        userAdapter.add(UserAdapter(User("Maria Hill", "mail@gmail.com", GradeType.SILVER)))
        userAdapter.add(UserAdapter(User("Falcon", "mail@gmail.com", GradeType.SILVER)))
        userAdapter.add(UserAdapter(User("Winter Soldier", "mail@gmail.com", GradeType.SILVER)))
        userAdapter.add(UserAdapter(User("Nick Fury", "mail@gmail.com", GradeType.GOLD)))
        userAdapter.add(UserAdapter(User("Ant-Man", "mail@gmail.com", GradeType.SILVER)))
        userAdapter.add(UserAdapter(User("Hulk Buster", "mail@gmail.com", GradeType.GOLD)))
        userAdapter.add(UserAdapter(User("Doctor Strange", "mail@gmail.com", GradeType.GOLD)))
        userAdapter.add(UserAdapter(User("Black Panther", "mail@gmail.com", GradeType.SILVER)))
        userAdapter.add(UserAdapter(User("Shuri", "mail@gmail.com", GradeType.BRONZE)))
        userAdapter.add(UserAdapter(User("Okoye", "mail@gmail.com", GradeType.BRONZE)))
    }
}