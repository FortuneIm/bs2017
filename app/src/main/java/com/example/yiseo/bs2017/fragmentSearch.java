package com.example.yiseo.bs2017;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
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

    static final String[] LIST_MENU = {"S1", "S2", "S3"} ;

    public EditText eText;

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
        /**
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, LIST_MENU) ;

        ListView listview = (ListView) view.findViewById(R.id.searchResult) ;
        listview.setAdapter(adapter) ;
*/
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
                final ListView searchResult = (ListView)getView().findViewById(R.id.searchResult);
                Log.i("tag","test0");

                Log.i("tag","test1");

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
                            List<Book> list = null;
                            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                            XmlPullParser parser = factory.newPullParser(); //연결하는거 담고
                            parser.setInput(new StringReader(data));
                            int eventType= parser.getEventType();
                            Book b = null;
                            while(eventType != XmlPullParser.END_DOCUMENT) {
                                switch (eventType) {
                                    case XmlPullParser.END_DOCUMENT://문서의 끝
                                        Log.i("tag","END_DOCU");
                                        break;
                                    case XmlPullParser.START_DOCUMENT:
                                        list = new ArrayList<Book>();
                                        Log.i("tag","START_DOCU");
                                        break;
                                    case XmlPullParser.END_TAG: {
                                        String tag = parser.getName();
                                        Log.i("tag","END_TAG");
                                        if(tag.equals("item")) {
                                            list.add(b);
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


                            ListView listView =(ListView)getView().findViewById(R.id.Searchimage);
                            ListAdapter adapter = new listAdapter(view.getContext());
                            listView.setAdapter(adapter);

                            /**int count=0;
                            while(count != list.size()) {
                                final Book finalB = new Book(list.get(count));
                                final int finalCount = count;
                                ((MainActivity) getContext()).runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(finalCount == 0)
                                            searchResult.setText("");
                                        if (finalB.getTitle() != null)
                                            searchResult.append(finalB.getTitle() +"\n");
                                    }
                                });
                                count++;
                            }*/
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
