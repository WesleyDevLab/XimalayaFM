package ximalayafm.beiing.com.ximalayafm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import ximalayafm.beiing.com.ximalayafm.fragments.AlbumDetailFragment;

public class AlbumDetailActivity extends FragmentActivity {


    public static void startAlbumDetailActivity(Context context, long albumId, long trackId){
        Intent intent = new Intent(context,AlbumDetailActivity.class);
        intent.putExtra(Constants.KEY_ALBUMID, albumId);
        intent.putExtra(Constants.KEY_TRACKID, trackId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//         requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_album_detail);

        Intent intent = getIntent();
        long albumId = intent.getLongExtra(Constants.KEY_ALBUMID, 0);
        long trackId = intent.getLongExtra(Constants.KEY_TRACKID, 0);

//        Toast.makeText(this, "albumid:" + albumId, Toast.LENGTH_SHORT).show();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.album_detail_container, AlbumDetailFragment.newInstance(albumId, trackId));
        transaction.commit();

    }


}
