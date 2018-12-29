package org.daimhim.plugin1.myweixin;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;

import org.daimhim.plugin1.R;


public class MainActivity extends TabActivity {
	private TabHost tabhost;
	private RadioGroup main_radiogroup;
	 private RadioButton tab_icon_weixin, tab_icon_address, tab_icon_friend,tab_icon_setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo);
        
        //获取按钮
        main_radiogroup = (RadioGroup) findViewById(R.id.main_radiogroup);
        
        tab_icon_weixin = (RadioButton) findViewById(R.id.tab_icon_weixin);
        tab_icon_address = (RadioButton) findViewById(R.id.tab_icon_address);
        tab_icon_friend = (RadioButton) findViewById(R.id.tab_icon_friend);
        tab_icon_setting = (RadioButton) findViewById(R.id.tab_icon_setting);
        
        //往TabWidget添加Tab
        tabhost = getTabHost();
        tabhost.addTab(tabhost.newTabSpec("tag1").setIndicator("0").setContent(new Intent(this,Activity1.class)));
        tabhost.addTab(tabhost.newTabSpec("tag2").setIndicator("1").setContent(new Intent(this,Activity2.class)));
        tabhost.addTab(tabhost.newTabSpec("tag3").setIndicator("2").setContent(new Intent(this,Activity3.class)));
        tabhost.addTab(tabhost.newTabSpec("tag4").setIndicator("3").setContent(new Intent(this,Activity4.class)));
         
        //设置监听事件
        checkListener checkradio = new checkListener();
        main_radiogroup.setOnCheckedChangeListener(checkradio);
    }

    
    //监听类
    public class checkListener implements OnCheckedChangeListener {
    	@Override
    	public void onCheckedChanged(RadioGroup group, int checkedId) {
    		// TODO Auto-generated method stub
    		//setCurrentTab 通过标签索引设置当前显示的内容
    		//setCurrentTabByTag 通过标签名设置当前显示的内容
    		switch(checkedId){
    		case R.id.tab_icon_weixin:
    			tabhost.setCurrentTab(0);
    			//或
    			//tabhost.setCurrentTabByTag("tag1");
    			break;
    		case R.id.tab_icon_address:
    			tabhost.setCurrentTab(1);
    			break;
    		case R.id.tab_icon_friend:
    			tabhost.setCurrentTab(2);
    			break;
    		case R.id.tab_icon_setting:
    			tabhost.setCurrentTab(3);
    			break;
    		}

    		
    	}
    }
   

}
