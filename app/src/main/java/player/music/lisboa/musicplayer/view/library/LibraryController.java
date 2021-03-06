package player.music.lisboa.musicplayer.view.library;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;
import com.bluelinelabs.conductor.RouterTransaction;
import com.bluelinelabs.conductor.support.RouterPagerAdapter;

import butterknife.BindView;
import player.music.lisboa.musicplayer.R;
import player.music.lisboa.musicplayer.view.base.BaseController;
import player.music.lisboa.musicplayer.view.library.albums.AlbumsController;
import player.music.lisboa.musicplayer.view.library.artists.ArtistsController;
import player.music.lisboa.musicplayer.view.library.genre.GenresController;
import player.music.lisboa.musicplayer.view.library.playlists.PlaylistsController;

/**
 * Created by Lisboa on 15-Jul-17.
 * <p>
 * Controller with tabbed views for albums, artists and genres
 */

public class LibraryController extends BaseController {

	public static final String LIBRARY_TAG = "Library";

	private static final String[] TABS = new String[]{"Albums", "Artists", "Genres", "Playlists"};

	@BindView(R.id.tab_layout)
	TabLayout tabLayout;
	@BindView(R.id.view_pager)
	ViewPager viewPager;

	private final RouterPagerAdapter pagerAdapter;

	public LibraryController() {
		pagerAdapter = new RouterPagerAdapter(this) {
			@Override
			public void configureRouter(@NonNull Router router, int position) {
				if (!router.hasRootController()) {
					Controller page;
					switch (position) {
						case 0:
							page = new AlbumsController();
							break;
						case 1:
							page = new ArtistsController();
							break;
						case 2:
							page = new GenresController();
							break;
						case 3:
							page = new PlaylistsController();
							break;
						default:
							page = new AlbumsController();
					}

					router.setRoot(RouterTransaction.with(page));
				}
			}

			@Override
			public int getCount() {
				return TABS.length;
			}

			@Override
			public CharSequence getPageTitle(int position) {
				return TABS[position];
			}
		};
		// TODO: 21-Jul-17 Play around with these method to figure out best config
		//pagerAdapter.setMaxPagesToStateSave();
		//viewPager.setOffscreenPageLimit();
	}

	@Override
	protected View inflateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container) {
		return inflater.inflate(R.layout.controller_library, container, false);
	}

	@Override
	public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
		inflater.inflate(R.menu.controller_library_menu, menu);
	}

	@Override
	protected void onViewBound(@NonNull View view) {
		super.onViewBound(view);

		viewPager.setAdapter(pagerAdapter);
		tabLayout.setupWithViewPager(viewPager);
	}

	@Override
	protected void onAttach(@NonNull View view) {
		super.onAttach(view);
	}

	@Override
	protected void onDestroyView(@NonNull View view) {
		if (!getActivity().isChangingConfigurations()) {
			viewPager.setAdapter(null);
		}
		tabLayout.setupWithViewPager(null);
		super.onDestroyView(view);
	}

	@Override
	protected String getTitle() {
		return "Library";
	}

}
