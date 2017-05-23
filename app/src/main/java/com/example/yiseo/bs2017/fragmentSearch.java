package com.example.yiseo.bs2017;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.zxing.integration.android.IntentIntegrator;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class fragmentSearch extends Fragment {

    public EditText eText;
    private ListViewAdapter adapter;
    private ListView searchResult;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
/**
    public void addItemonSpinner(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_search, container, false);
        Spinner spinner1 = (Spinner) v.findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getContext(), R.array.book_name, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(getBaseContext(), parent.getItemAtPosition(position))
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
       // spinner1.setOnItemSelectedListener(this.get);
    }*/

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
       // addItemonSpinner(inflater, container, savedInstanceState);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        final View view = inflater.inflate(R.layout.fragment_search, container, false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new IntentIntegrator(getActivity()).initiateScan();
            }
        });

        //------------------------------------------------------------------------------------------

        Button Search_btn = (Button) view.findViewById(R.id.OKBtn);
        Search_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View v){
                searchResult = (ListView)getView().findViewById(R.id.searchResult);
                adapter = new ListViewAdapter();
                searchResult.setAdapter(adapter);

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String clientID="VcCW0iW9uecJE4GN0SUx";
                        String clientSecret = "yVvAGbuj6T";
                        Log.i("tag","1111111111111");
                        eText = (EditText)getView().findViewById(R.id.searchTarget);
                        Log.i("tag","22222222222");

                        try {
                            String str = eText.getText().toString();
                            String myQuery = URLEncoder.encode(str);
                            URL url = new URL("https://openapi.naver.com/v1/search/book.xml?query="+myQuery);
                            URLConnection urlConn=url.openConnection();

                            URL ImageUrl;
                            Bitmap bmp;
                            Log.i("tag","ask3");
                            urlConn.setRequestProperty("X-Naver-Client-ID", clientID);
                            urlConn.setRequestProperty("X-Naver-Client-Secret", clientSecret);

                            BufferedReader br = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

                            String data="";
                            String msg = null;
                            while((msg = br.readLine())!=null)
                            {
                                System.out.println(msg);
                                data += msg;
                            }
                            List<Book> Booklist = null;
                            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                            XmlPullParser parser = factory.newPullParser(); //연결하는거 담고
                            parser.setInput(new StringReader(data));
                            int eventType= parser.getEventType();

                            // book에 저장하는 thread 끝나면 ListViewAdapter에 추가하는 루틴을 다시 추가하자.

                            Book b = null;
                            while(eventType != XmlPullParser.END_DOCUMENT) {
                                switch (eventType) {
                                    case XmlPullParser.END_DOCUMENT://문서의 끝
                                        Log.i("tag","END_DOCU");
                                        break;
                                    case XmlPullParser.START_DOCUMENT:
                                        // 문서가 시작되는 경우 BOOK을 저장하는 List 형성
                                        Booklist = new ArrayList<Book>();

                                        break;

                                    case XmlPullParser.END_TAG: {
                                        String tag = parser.getName();
                                        Log.i("tag","END_TAG");
                                        if(tag.equals("item")) {
                                            // listview에 더하기
                                            Booklist.add(b);
                                            // addItem(Drawable icon, String title, String author, String publisher)
                                            adapter.addItem(b.getImag(), b.getTitle(), b.getAuthor(),
                                                    b.getPublisher());
                                            b = null;
                                        }
                                    }
                                    case XmlPullParser.START_TAG:{ //무조건 시작하면 만남
                                        String tag = parser.getName();
                                        Log.i("tag","STartTag");
                                        switch(tag){
                                            case "item": //item가 열렸다는것은 새로운 책이 나온다는것
                                                b = new Book();
                                                break;
                                            case "title":
                                                if(b!=null) {
                                                    b.setTitle(parser.nextText());
                                                    Log.i("tag","title");
                                                }
                                                break;
                                            case "link":
                                                if(b!=null) {
                                                    b.setLink(parser.nextText());
                                                    Log.i("tag","link");}
                                                break;
                                            case "image":
                                                if(b!=null) {
                                                    b.setImag(parser.nextText());
                                                    Log.i("tag","image");
                                                }
                                                break;
                                            case "author":
                                                if(b!=null)
                                                    b.setAuthor(parser.nextText());
                                                break;
                                            case "price":
                                                if(b!=null)
                                                    b.setPrice(parser.nextText());
                                                break;
                                            case "discount":
                                                if(b!=null)
                                                    b.setDiscount(parser.nextText());
                                                break;
                                            case "pubdate":
                                                if(b!=null)
                                                    b.setPubdate(parser.nextText());
                                                break;
                                            case "isbn":
                                                if(b!=null)
                                                    b.setIsbn(parser.nextText());
                                                break;
                                            case "description":
                                                if(b!=null)
                                                    b.setDescription(parser.nextText());
                                                break;
                                        }
                                        break;
                                    }
                                }
                                eventType = parser.next();
                            }

                            } catch (MalformedURLException e) {
                            e.printStackTrace();
                            Log.i("tag","exception1");
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.i("tag","exception2");
                        } catch (XmlPullParserException e) {
                            e.printStackTrace();
                            Log.i("tag","exception3");
                        }
                    }
                });
                Log.i("tag","test2");
                thread.start();
                Log.i("tag","test3");
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Search");
    }


}
