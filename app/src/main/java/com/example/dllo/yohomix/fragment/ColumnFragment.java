package com.example.dllo.yohomix.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.yohomix.R;
import com.example.dllo.yohomix.adapter.ColumnAdapter;
import com.example.dllo.yohomix.base.BaseFragment;
import com.example.dllo.yohomix.bean.ColumnBean;
import com.example.dllo.yohomix.bean.ColumnPicBean;
import com.example.dllo.yohomix.listener.NetListener;
import com.example.dllo.yohomix.tools.MyApp;
import com.example.dllo.yohomix.tools.URLValues;
import com.example.dllo.yohomix.tools.VolleySingleton;
import com.example.dllo.yohomix.widget.CircleDrawable;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.HashMap;

/**
 * Created by dllo on 16/11/23.
 */

public class ColumnFragment extends BaseFragment {
    private ListView mListView;
    private ColumnAdapter mAdapter;

    private TextView title, description, answerNum, answer, question, nick;
    private ImageView banner, headPic, titlePic;

    @Override
    protected int setLayout() {
        return R.layout.fragment_column;
    }

    @Override
    protected void initView() {
        mListView = bindView(R.id.column_list);
        addHeader();
        mAdapter = new ColumnAdapter();

    }

    @Override
    protected void initData() {

        HashMap<String, String> map = new HashMap<>();
        map.put(URLValues.POST_KEY, URLValues.COLUMN_VALUE);
        VolleySingleton.MyRequest(URLValues.COLUMN_URL, ColumnBean.class, new NetListener<ColumnBean>() {
            @Override
            public void successListener(ColumnBean response) {
                mAdapter.setBean(response);
                mListView.setAdapter(mAdapter);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        }, map);
    }

    public void addHeader() {

        View view = LayoutInflater.from(MyApp.getContext()).inflate(R.layout.column_header, null);
        title = (TextView) view.findViewById(R.id.title);
        description = (TextView) view.findViewById(R.id.description);
        answerNum = (TextView) view.findViewById(R.id.answer_number);
        answer = (TextView) view.findViewById(R.id.answer);
        nick = (TextView) view.findViewById(R.id.nick);
        question = (TextView) view.findViewById(R.id.question);
        banner = (ImageView) view.findViewById(R.id.column_banner);
        headPic = (ImageView) view.findViewById(R.id.head_pick);
        titlePic = (ImageView) view.findViewById(R.id.title_pic);

        HashMap<String, String> map = new HashMap<>();
        map.put(URLValues.POST_KEY, URLValues.COLUMN_PICTURE_VALUE);

        VolleySingleton.MyRequest(URLValues.COLUMN_PICTURE_URL, ColumnPicBean.class, new NetListener<ColumnPicBean>() {
            @Override
            public void successListener(ColumnPicBean response) {
                description.setText(response.getData().getDescription());
                title.setText(response.getData().getTitle());
                answerNum.setText(String.valueOf(response.getData().getAnswers()));
                Picasso.with(getActivity()).load(response.getData().getBanner()).into(banner);
                question.setText(response.getData().getData().get(0).getQuestion());
                answer.setText(response.getData().getData().get(0).getAnswer());
                nick.setText(response.getData().getData().get(0).getNick());
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.static_shoe);
                CircleDrawable circleDrawable = new CircleDrawable(bitmap);
                titlePic.setImageDrawable(circleDrawable);
                Picasso.with(getActivity()).load(response.getData().getData().get(0).getHeadpic()).transform(new CircleTransform()).into(headPic);
            }

            @Override
            public void errorListener(VolleyError error) {

            }
        }, map);
        mListView.addHeaderView(view);
    }

    class CircleTransform implements Transformation {
        @Override
        public Bitmap transform(Bitmap source) {
            int r = Math.min(source.getWidth() / 2, source.getHeight() / 2);

            int x = (source.getWidth() - r)/ 2;
            int y = (source.getHeight() - r ) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(source, x, y, r, r);
            if (squaredBitmap != source) {
                source.recycle();
            }

            Bitmap bitmap = Bitmap.createBitmap(r, r, source.getConfig());

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);

            canvas.drawCircle(x, y, r, paint);

            squaredBitmap.recycle();
            return bitmap;
        }

        @Override
        public String key() {
            return "circle";
        }
    }
}
