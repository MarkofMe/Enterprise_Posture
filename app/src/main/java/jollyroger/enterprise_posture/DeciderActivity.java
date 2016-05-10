package jollyroger.enterprise_posture;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class DeciderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decider);
    }

    public void TakeAnotherPicture(View view) {
    }

    public void AddToNewPatient(View view) {
        startActivity(new Intent(this, DatabaseFragmentCreatePatientActivity.class));
    }

    public void AddToExistingPatient(View view) {
        startActivity(new Intent(this, AddToPatientActivity.class));
    }

}
