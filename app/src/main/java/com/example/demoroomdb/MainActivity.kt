package com.example.demoroomdb

import android.content.DialogInterface
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.demoroomdb.data.UserRepository
import com.example.demoroomdb.data.local.UserDatabase
import com.example.demoroomdb.data.local.UserLocalDataSource
import com.example.demoroomdb.data.model.BankAccount
import com.example.demoroomdb.data.model.User
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemLongClickListener,
    AdapterView.OnItemClickListener {

    private var compositeDisposable: CompositeDisposable? = null
    private var listView: ListView? = null
    private var userRepository: UserRepository? = null
    private var users: MutableList<User>? = mutableListOf()
    private var adapter: ArrayAdapter<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        compositeDisposable = CompositeDisposable()

        listView = findViewById(R.id.list_user)
        listView!!.onItemLongClickListener = this
        listView!!.onItemClickListener = this
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, users!!)
        listView!!.adapter = adapter

        val userDatabase = UserDatabase.getInstance(this)
        if (userDatabase?.userDao() != null)
            userRepository = UserRepository.getInstance(UserLocalDataSource.getInstance(userDatabase.userDao()))

        ivPlus.setOnClickListener {
            Log.e("checkClick", "ok")
            val disposable = Observable.create(ObservableOnSubscribe<Any> { e ->
                val user = User( name = "Tin " + Random().nextInt())
                val bank = BankAccount("Account name: " + Random().nextInt())
                user.bankAccount = bank
                userRepository!!.insertUser(user)
                e.onComplete()
            })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    getData()
                }, {
                    onGetAllUserFailure(it.message)
                })

            compositeDisposable!!.add(disposable)
        }
//        ivPlus.setOnClickListener {

//        }
        getData()
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable!!.clear()
    }

    private fun getData() {
        val disposable = userRepository!!.getALlUser()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                { users -> onGetAllUserSuccess(users) },
                { throwable -> onGetAllUserFailure(throwable.message) })

        compositeDisposable!!.add(disposable)
    }

    private fun onGetAllUserFailure(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun onGetAllUserSuccess(userList: List<User>) {
        users!!.clear()
        users!!.addAll(userList)
        adapter!!.notifyDataSetChanged()
    }

    private fun deleteAllUser() {
        val disposable = Observable.create(ObservableOnSubscribe<Any> { e ->
            userRepository!!.deleteAllUser()
            e.onComplete()
        })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                getData()
            }, {
                onGetAllUserFailure(it.message)
            })

        compositeDisposable!!.add(disposable)
    }

    /**
     * Edit User Last Name when click at item
     */
    override fun onItemClick(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
        val user = users!![i]
        val editText = EditText(this)
        editText.setText(user.name)
        editText.setHint("Input name")
        AlertDialog.Builder(this).setTitle("Edit")
            .setMessage("Edit user name")
            .setView(editText)
            .setPositiveButton(android.R.string.ok,
                DialogInterface.OnClickListener { dialogInterface, i ->
                    if (TextUtils.isEmpty(editText.text.toString())) {
                        return@OnClickListener
                    }
                    user.name = editText.text.toString()
                    updateUser(user)
                })
            .setNegativeButton(android.R.string.cancel, null)
            .create()
            .show()
    }

    fun updateUser(user: User) {
        val disposable = Observable.create(ObservableOnSubscribe<Any> { e ->
            userRepository!!.updateUser(user)
            e.onComplete()
        })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                getData()
            }, {
                onGetAllUserFailure(it.message)
            })

        compositeDisposable!!.add(disposable)
    }

    /**
     * Confirm delete item when long click
     */
    override fun onItemLongClick(
        adapterView: AdapterView<*>,
        view: View,
        i: Int,
        l: Long
    ): Boolean {
        val user = users!![i]
        AlertDialog.Builder(this).setTitle("Delete")
            .setMessage("Do you want to delete " + user.toString())
            .setPositiveButton(android.R.string.ok,
                DialogInterface.OnClickListener { dialogInterface, i -> deleteUser(user) })
            .setNegativeButton(android.R.string.cancel, null)
            .create()
            .show()
        return false
    }

    private fun deleteUser(user: User) {
        val disposable = Observable.create(ObservableOnSubscribe<Any> { e ->
            userRepository!!.deleteUser(user)
            e.onComplete()
        })
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    getData()
                }, {
                    onGetAllUserFailure(it.message)
                })
//                Consumer { throwable -> onGetAllUserFailure(throwable.message) },
//                Action { getData() })

        compositeDisposable!!.add(disposable)
    }
}
