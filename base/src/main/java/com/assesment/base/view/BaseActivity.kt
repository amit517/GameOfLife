package com.assesment.base.view

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity() {
    protected val bindingView: VB
        get() = viewDataBinding
    private lateinit var viewDataBinding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, getLayout())
        setContentView(viewDataBinding.root)

        initOnCreateView()
    }

    @LayoutRes
    abstract fun getLayout(): Int

    abstract fun initOnCreateView()
}
