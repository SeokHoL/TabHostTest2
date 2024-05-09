package kr.ac.kopo.tabhosttest2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() , ActionBar.TabListener {
    lateinit var tab1  : ActionBar.Tab
    lateinit var tab2  : ActionBar.Tab
    lateinit var tab3  : ActionBar.Tab

    var arrFrags = arrayOfNulls<MyFragment>(3)
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var bar = this.supportActionBar
        bar!!.navigationMode = ActionBar.NAVIGATION_MODE_TABS
        
        tab1 = bar.newTab()
        tab1.text = "첫번째탭"
        tab1.setTabListener(this)
        bar.addTab(tab1)

        tab2 = bar.newTab()
        tab2.text = "두번째탭"
        tab2.setTabListener(this)
        bar.addTab(tab2)

        tab3 = bar.newTab()
        tab3.text = "세번째탭"
        tab3.setTabListener(this)
        bar.addTab(tab3)


        setContentView(R.layout.activity_main)
    }

    override fun onTabSelected(tab: ActionBar.Tab?, ft: FragmentTransaction?) {
        var fragment : MyFragment? =null

        if(arrFrags[tab!!.position] == null) {
            fragment = MyFragment()
            var data = Bundle()
            data.putString("tabName", tab.text.toString())
            fragment.arguments = data
            arrFrags[tab.position] = fragment
        }else{
            fragment = arrFrags[tab.position]
        }

        ft!!.replace(android.R.id.content, fragment!!)
    }

    override fun onTabUnselected(tab: ActionBar.Tab?, ft: FragmentTransaction?) {
        TODO("Not yet implemented")
    }

    override fun onTabReselected(tab: ActionBar.Tab?, ft: FragmentTransaction?) {
        TODO("Not yet implemented")
    }

    //탭을 선택하면 변경되는 content로 Fragment를 사용
    class MyFragment : Fragment(){
        var tabName : String? =null //tabName 초기화
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            var data = arguments
            tabName =data!!.getString("tabName")
        }

        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            var params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
            var linear = LinearLayout(super.getActivity())
            linear.layoutParams = params
            linear.orientation = LinearLayout.VERTICAL

            if(tabName === "첫번째탭")
                linear.setBackgroundColor(Color.MAGENTA)
            if(tabName === "두번째탭")
                linear.setBackgroundColor(Color.YELLOW)
            if(tabName === "세번째탭")
                linear.setBackgroundColor(Color.GREEN)

            
            return linear
        }
    }
}