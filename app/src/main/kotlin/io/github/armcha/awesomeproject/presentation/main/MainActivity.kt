package io.github.armcha.awesomeproject.presentation.main

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import io.github.armcha.awesomeproject.R
import io.github.armcha.awesomeproject.injection.Injection
import io.github.armcha.awesomeproject.model.User
import io.github.armcha.awesomeproject.presentation.adapter.UserAdapter
import io.github.armcha.awesomeproject.presentation.base.BaseActivity
import io.github.armcha.awesomeproject.presentation.hide
import io.github.armcha.awesomeproject.presentation.onClick
import io.github.armcha.awesomeproject.presentation.show
import io.github.armcha.awesomeproject.presentation.widget.InputDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainContract.View, MainContract.Presenter>(),
        MainContract.View, InputDialog.InputDialogListener {

    override fun initPresenter() = Injection.provideMainPresenter()
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        presenter.onStart()
        fab.onClick { presenter.handleFabClick() }
    }

    override fun showLoading() {
        progressBar.show()
    }

    override fun hideLoading() {
        progressBar.hide()
    }

    override fun showAddButton() {
        fab.show()
    }

    override fun showAddUserDialog() {
        InputDialog().apply { show(this@MainActivity.supportFragmentManager, tag) }
    }

    override fun setUpUserList(list: List<User>) {
        userAdapter = UserAdapter(list.toMutableList())
        with(recyclerView) {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
        }
    }

    override fun addNewUserToList(user: User) {
        userAdapter.addNewUser(user)
        recyclerView.smoothScrollToPosition(userAdapter.itemCount)
    }

    override fun onDialogPositiveAction(name: String, surname: String) {
        presenter.createNewUser(name, surname)
    }
}
