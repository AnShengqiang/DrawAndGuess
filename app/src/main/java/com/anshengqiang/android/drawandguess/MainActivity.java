package com.anshengqiang.android.drawandguess;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.anshengqiang.android.drawandguess.tools.ColorPickerFragment;
import com.anshengqiang.android.drawandguess.tools.WidthPickerFragment;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private static final String DIALOG_COLOR = "DialogColor";
    private static final String DIALOG_PEN_WIDTH = "DialogPenWidth";

    private static final int REQUEST_COLOR = 0;

    private Button mFreeTypeButton;
    private Button mPointTypeButton;
    private Button mLineTypeButton;
    private Button mCurveTypeButton;

    private Button mColorButton;
    private Button mWidthButton;
    private Button mDeleteButton;
    private Button mClearButton;

    private SceneView mSceneView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mFreeTypeButton = (Button) findViewById(R.id.free_type_button);
        mFreeTypeButton.setOnClickListener(this);
        mPointTypeButton = (Button) findViewById(R.id.draw_point_button);
        mPointTypeButton.setOnClickListener(this);
        mLineTypeButton = (Button) findViewById(R.id.draw_line_button);
        mLineTypeButton.setOnClickListener(this);
        mCurveTypeButton = (Button) findViewById(R.id.draw_curve_button);
        mCurveTypeButton.setOnClickListener(this);
        mColorButton = (Button) findViewById(R.id.color_button);
        mColorButton.setOnClickListener(this);
        mWidthButton = (Button) findViewById(R.id.width_button);
        mWidthButton.setOnClickListener(this);
        mDeleteButton = (Button) findViewById(R.id.delete_last_button);
        mDeleteButton.setOnClickListener(this);
        mClearButton = (Button) findViewById(R.id.clear_button);
        mClearButton.setOnClickListener(this);

        mSceneView = (SceneView) findViewById(R.id.scene_view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.free_type_button:
                mSceneView.setType(0);
                break;
            case R.id.draw_point_button:
                mSceneView.setType(1);
                break;
            case R.id.draw_line_button:
                mSceneView.setType(2);
                break;
            case R.id.draw_curve_button:
                mSceneView.setType(3);
                break;
            case R.id.color_button:
                FragmentManager manager = getSupportFragmentManager();
                int color = mSceneView.getColor();
                ColorPickerFragment colorPickerFragment = new ColorPickerFragment();
                colorPickerFragment.show(manager, DIALOG_COLOR);
                break;
            case R.id.width_button:
                FragmentManager manager1 = getSupportFragmentManager();
                WidthPickerFragment widthPickerFragment = WidthPickerFragment.newInstance(mSceneView.getLineWidth());
                widthPickerFragment.show(manager1, DIALOG_PEN_WIDTH);
                break;
            case R.id.delete_last_button:
                if (mSceneView.getDoodleList().getDoodles().size() > 0){
                    mSceneView.getDoodleList().deleteLast();
                    mSceneView.setLineListIndex(mSceneView.getLineListIndex() - 1);
                }
                break;
            case R.id.clear_button:
                mSceneView.getDoodleList().clear();
                mSceneView.setLineListIndex(-1);
                break;
            default:
                break;
        }
    }

    public void setColor(int color){
        mSceneView.setColor(color);
    }

    public void setPenWidth(int width){
        mSceneView.setLineWidth(width);
    }


}
