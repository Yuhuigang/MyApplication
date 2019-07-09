package cn.ident.nas.tester;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.TagLostException;
import android.nfc.tech.NfcV;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;

public class Normal_15693Activity extends BaseNfcActivity {
    private TextView tv_tag_id;
    private LinearLayout layout_button;
    private Button btn_read,btn_write,btn_password_change,btn_password_validation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal_15693);
        tv_tag_id=findViewById(R.id.tv_tag_id);
        layout_button=findViewById(R.id.layout_button);
        btn_read=findViewById(R.id.btn_read);
        btn_write=findViewById(R.id.btn_write);
        btn_password_change=findViewById(R.id.btn_password_change);
        btn_password_validation=findViewById(R.id.btn_password_validation);
        btn_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Normal_15693Activity.this,ReadActivity.class);
                startActivity(intent);
            }
        });
        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Normal_15693Activity.this,WriteActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onNewIntent(Intent intent) {
        NfcAdapter aNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        readNfcId(intent);
    }
    //字节处理
    private static String getTid(byte[] bytes){
        StringBuilder sb=new StringBuilder();
        for (int i =bytes.length - 1;i>= 0;--i){
            int b = bytes[i] & 0xff;
            if (b < 0x10)
                sb.append('0');
            sb.append(Integer.toHexString(b));
            if (i > 0) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
    //获取标签UID
    public void readNfcId(Intent intent){

        try {
            Tag tag=intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            NfcV nfcV=NfcV.get(tag);
            nfcV.connect();
            Tag nfcvtag=nfcV.getTag();
            StringBuilder sb = new StringBuilder();
            sb.append("Tid:"+getTid(nfcvtag.getId()));
            tv_tag_id.setText(sb.toString());
            layout_button.setVisibility(View.VISIBLE);

        } catch (TagLostException te){
            tv_tag_id.setText("");
            layout_button.setVisibility(View.GONE);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
