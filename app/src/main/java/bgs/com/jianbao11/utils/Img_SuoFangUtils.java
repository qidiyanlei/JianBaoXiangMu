package bgs.com.jianbao11.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
/**
 * 双指放大的工具类
 * */
public class Img_SuoFangUtils extends View{
	private Bitmap bitmap;//要操作的图片
	private Matrix matrix=new Matrix();//使用矩阵来缩放位移图片
	private boolean touchFlag=true;//触摸的标志  一个手指点为true  两个手指点为false
	private PointF pf=new PointF();//用来记录手指按下的坐标的类
	private boolean isFirst=true;//图片是否第一次进入
	private float dis=0f;//两指之间的距离
	private float midx=0f;//两指之间X轴的中心坐标
	private float midy=0f;//两指之间Y轴的中心坐标
	private float maxScale=5f;//最大的放大倍数
	private float minScale=0.5f;//最小的缩小倍数
	private float [] array=new float[9];//数组里的9个值
	
	public Img_SuoFangUtils(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	//最大放大的倍数
	private void setMaxScale(float maxScale){
		if (maxScale>5||maxScale<0.5) {
			return;
		}
		this.maxScale=maxScale;
	}
	//最小缩小的倍数
	private void setMinScale(float minScale){
		if (minScale>5||minScale<0.5) {
			return;
		}
		this.minScale=minScale;
	}
	//设置图片的方法
	public void setBitmap(Bitmap bitmap){
		isFirst=true;
		this.bitmap=bitmap;
		invalidate();
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		if (isFirst&bitmap!=null) {
			float wScale=getMeasuredWidth()/bitmap.getWidth();//屏幕相对于图片宽的倍数
			float hScale=getMeasuredHeight()/bitmap.getHeight();//屏幕相对于图片高的倍数
			float Scale=Math.min(wScale, hScale);//取两个倍数的最小值
			//以这个最小的值来缩放原始的图片使其适配屏幕
			matrix.postScale(Scale, Scale);
			//累乘
			maxScale*=Scale;
			minScale*=Scale;
			//获得图片初始位置到自定义View中心点距离   将图片位移到屏幕的中心
			float disOx=getMeasuredWidth()/2-bitmap.getWidth()*Scale/2;
			float disOy=getMeasuredHeight()/2-bitmap.getHeight()*Scale/2;
			matrix.postTranslate(disOx, disOy);
		}
		isFirst=false;
	}
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		if (bitmap!=null) {
			//将处理过得图片画到View上
			canvas.drawBitmap(bitmap, matrix, null);
		}
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction()==MotionEvent.ACTION_DOWN) {
			//第一个手指按下的坐标
			touchFlag=true;
			pf.x=event.getX();
			pf.y=event.getY();
		}else if (event.getAction()==MotionEvent.ACTION_POINTER_2_DOWN) {
			//第二个手指按下
			touchFlag=false;
			float twoX=event.getX();
			float twoY=event.getY();
			//计算移动之前两指之间的距离
			dis=(float) Math.sqrt(Math.pow(event.getX(0)-event.getY(1),2)+
					Math.pow(event.getY(0)-event.getY(1), 2));
			//获得两指的中心的坐标   以这个坐标来缩放图片
			midx=(event.getX(0)+event.getX(1))/2;
			midy=(event.getY(0)+event.getY(1))/2;
		}else if (event.getAction()==MotionEvent.ACTION_MOVE) {
			if (touchFlag&&event.getPointerCount()==1) {
				//一根手指滑动
				float nowoneX=event.getX();
				float nowoneY=event.getY();
				//计算滑动距离
				float onedisX=nowoneX-pf.x;
				float onedisY=nowoneY-pf.y;
				//将抹点的坐标再次作为移动的初始点
				pf.x=nowoneX;
				pf.y=nowoneY;
				//图片位移
				matrix.postTranslate(onedisX, onedisY);
			}else if (!touchFlag&&event.getPointerCount()==2) {
				//两根手指滑动
				//滑动后两指之间的距离
				float nowdis=(float) Math.sqrt(Math.pow(event.getX(0)-event.getY(1),2)+
						Math.pow(event.getY(0)-event.getY(1), 2));
				//获取图片的缩放倍数
				float twoScale=nowdis/dis;
				//矩阵中获取9个值
				matrix.getValues(array);
				//将缩放后的距离当做再次缩放的初始值
				dis=nowdis;
				//缩放移动边界的判断
				if (array[0]>=maxScale&&twoScale>=1) {
					return true;
				}
				if (array[0]<=minScale&&twoScale<1) {
					return true;
				}
				//设置缩放
				matrix.postScale(twoScale, twoScale, midx, midy);
			}
			invalidate();
		}
		return true;
	}
}
